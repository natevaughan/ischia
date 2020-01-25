package io.ischia.controller

import io.ischia.domain.Activity
import io.ischia.domain.ActivityDAO
import io.ischia.domain.User
import io.ischia.logger
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/rest")
class ActivityRestController(private val activityDAO: ActivityDAO) {

	companion object {
		private val log by logger()
	}

	@RequestMapping("/activity")
	@ResponseBody
	fun login(): List<Activity> {
		val auth = SecurityContextHolder.getContext().authentication
		log.info("User was ${auth.principal}")
//		return activityDAO.findAllByCreator()
		val user = User("foo@bar.com")
		return listOf(Activity("Hiking", user, "hiking"), Activity("Sailing", user, "sailing"))
	}
}