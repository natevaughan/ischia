package io.ischia.controller

import io.ischia.logger
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
	fun home(): String {
		// real high tech stuff here wow amazing
		return "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Ischia Home</title></head><body><h3>Ischia Home</h3><div>You are logged in as </div></body></html>"
	}
}