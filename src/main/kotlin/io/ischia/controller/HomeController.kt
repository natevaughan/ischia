package io.ischia.controller

import io.ischia.UserService
import io.ischia.logger
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HomeController(private val userService: UserService) {

	companion object {
		private val log by logger()
	}

	@RequestMapping("/")
	@ResponseBody
	fun login(): String {
		val auth = SecurityContextHolder.getContext().authentication
		log.info("User was ${auth.principal}")
		return "<!DOCTYPE html><html><body><div>Welcome!</div><div><a href=\"/login\">log in with google</a></div></body></html>"
	}

	@RequestMapping("/callback")
	@ResponseBody
	fun callback(): String {
		val auth = SecurityContextHolder.getContext().authentication
		log.info("User was ${auth.principal}")
		return "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Home</title></head><body>You are logged in.</body></html>"
	}
}