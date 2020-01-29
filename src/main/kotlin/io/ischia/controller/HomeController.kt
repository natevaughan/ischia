package io.ischia.controller

import io.ischia.UserService
import io.ischia.logger
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController() {

	companion object {
		private val log by logger()
	}

	@RequestMapping("/")
	@ResponseBody
	fun home(auth: Authentication): String {
		val user = auth.principal as DefaultOAuth2User
		// real high tech stuff here wow amazing
		return "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Ischia Home</title></head><body><h3>Ischia Home</h3><div>You are logged in as <a href=\"${user.attributes["html_url"]}\">${user.attributes["name"]}</a></div></body></html>"
	}
}