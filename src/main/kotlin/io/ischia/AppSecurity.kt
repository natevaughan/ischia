package io.ischia

import io.ischia.web.RequestLoggingFilter
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter

@Configuration
@EnableOAuth2Sso
open class AppSecurity: WebSecurityConfigurerAdapter() {
	override fun configure(http: HttpSecurity) {
		http
				// register our request logging filter at the front of the Spring Security filter chain
				.addFilterBefore(RequestLoggingFilter(), WebAsyncManagerIntegrationFilter::class.java)
				.antMatcher("/**")
				.authorizeRequests()
				.antMatchers("/", "/callback", "/login**", "/webjars", "/error**")
				.permitAll()
				.anyRequest()
				.authenticated()
	}
}