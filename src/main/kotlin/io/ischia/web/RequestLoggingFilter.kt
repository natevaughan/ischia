package io.ischia.web

import com.google.common.base.Stopwatch
import io.ischia.logger
import java.util.concurrent.TimeUnit
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestLoggingFilter: Filter {

	companion object {
		private val log by logger()
		private val EXCLUDED_LOGGING_PATHS = setOf("/health-check")
		private const val HEADER_X_FORWARDED_FOR = "X-Forwarded-For"
	}

	override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
		request as HttpServletRequest
		response as HttpServletResponse
		val stopwatch = Stopwatch.createStarted()
		val logRequest = !EXCLUDED_LOGGING_PATHS.contains(request.requestURI)
		val ip = request.getHeader(HEADER_X_FORWARDED_FOR) ?: ""

		chain.doFilter(request, response)
		if (logRequest) {
			log.info("${request.method} ${request.requestURI} : ${response.status} ${stopwatch.elapsed(TimeUnit.MILLISECONDS)} ms $ip")
		}
	}
}