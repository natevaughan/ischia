package io.ischia

import io.ischia.web.RequestTimingFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter

@Configuration
open class AppSecurityConfig(private val config: TypedConfig): WebSecurityConfigurerAdapter() {
	override fun configure(http: HttpSecurity) {
		http
				// register our request logging filter at the front of the Spring Security filter chain
				.addFilterBefore(RequestTimingFilter(config), WebAsyncManagerIntegrationFilter::class.java)
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.oauth2Login()
	}

	@Bean
	open fun clientRegistrationRepository(): ClientRegistrationRepository {
		return InMemoryClientRegistrationRepository(googleClientRegistration(), githubClientRegsitration())
	}

	private fun googleClientRegistration(): ClientRegistration {
		return CommonOAuth2Provider.GOOGLE.getBuilder("google")
				.clientId(config.getString("ischia.oauth2.google.client-id"))
				.clientSecret(config.getString("ischia.oauth2.google.client-secret"))
				.redirectUriTemplate(config.getString("ischia.oauth2.google.redirect-uri"))
				.build()
	}

	private fun githubClientRegsitration(): ClientRegistration {
		return CommonOAuth2Provider.GITHUB.getBuilder("github")
				.clientId(config.getString("ischia.oauth2.github.client-id"))
				.clientSecret(config.getString("ischia.oauth2.github.client-secret"))
				.redirectUriTemplate(config.getString("ischia.oauth2.github.redirect-uri"))
				.build()
	}
}