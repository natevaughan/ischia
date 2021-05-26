package io.ischia.controller

import io.ischia.UserService
import io.ischia.domain.Activity
import io.ischia.domain.ActivityDAO
import io.ischia.logger
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/rest")
class ActivityRestController(private val activityDAO: ActivityDAO, private val userService: UserService) {

	companion object {
		private val log by logger()
	}

	@RequestMapping("/activity")
	@ResponseBody
	fun getActivityForUser(auth: Authentication): List<Activity> {
		val user = auth.principal as DefaultOAuth2User
		val dbUser = userService.findByEmail(user.name)
		log.info("User ${dbUser.id} accessed activity")
		return activityDAO.findAllByCreator(dbUser)
	}
}