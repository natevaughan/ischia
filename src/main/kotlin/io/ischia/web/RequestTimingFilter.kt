package io.ischia.web

import com.google.common.base.Stopwatch
import io.ischia.TypedConfig
import io.ischia.logger
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Example javax.servlet.Filter to log request times
 *
 * A servlet filter can be wired into spring using @Component
 * or @Named annotations, or can be explicitly placed in order
 * (as this one is) in AppSecurityConfig
 */
@Named
class RequestTimingFilter(private val config: TypedConfig): Filter {

	companion object {
		private val log by logger()
		private val EXCLUDED_LOGGING_PATHS = setOf("/health-check", "/favicon.ico")
		private const val HEADER_X_FORWARDED_FOR = "X-Forwarded-For"
		private const val KEY_ISCHIA_TIME_REQUESTS = "ischia.time.requests"
	}

	override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
		request as HttpServletRequest
		response as HttpServletResponse
		val stopwatch = Stopwatch.createStarted()
		val logRequest = !EXCLUDED_LOGGING_PATHS.contains(request.requestURI) &&
				config.getBoolean(KEY_ISCHIA_TIME_REQUESTS)
		val ip = request.getHeader(HEADER_X_FORWARDED_FOR) ?: ""

		chain.doFilter(request, response)
		if (logRequest) {
			log.info("${request.method} ${request.requestURI} : ${response.status} ${stopwatch.elapsed(TimeUnit.MILLISECONDS)} ms $ip")
		}
	}
}