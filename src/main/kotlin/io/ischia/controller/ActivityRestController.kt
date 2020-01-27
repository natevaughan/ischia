package io.ischia.controller

import io.ischia.domain.Activity
import io.ischia.domain.ActivityDAO
import io.ischia.domain.User
import io.ischia.logger
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
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
	fun login(auth: Authentication): List<Activity> {
		val user = auth.principal as DefaultOAuth2User
//		return activityDAO.findAllByCreator()
		val creator = User(user.attributes["email"] as String? ?: "foo@bar.com")
		return listOf(Activity("Hiking", creator, "hiking"), Activity("Sailing", creator, "sailing"))
	}
}