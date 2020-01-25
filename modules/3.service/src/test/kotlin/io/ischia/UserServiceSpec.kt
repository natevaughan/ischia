package io.ischia

import io.ischia.domain.User
import io.ischia.domain.UserDAO
import io.ischia.exception.NotFoundException
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.util.Optional

class UserServiceSpec {

	private val USER_ID = 42L
	private val user = User("foo@bar.com", USER_ID)
	private val userDAO = mock(UserDAO::class.java)
	private val service = UserServiceImpl(userDAO)

	@Test(expected = NotFoundException::class)
	fun itShouldThrowAnExceptionIfOptionalIsEmpty() {
		`when`(userDAO.findById(USER_ID)).thenReturn(Optional.empty())
		service.findById(USER_ID)
	}

	@Test
	fun itShouldReturnTheUser() {
		`when`(userDAO.findById(USER_ID)).thenReturn(Optional.of(user))
		val returned = service.findById(USER_ID)
		assertEquals("It should return the user", user, returned)
	}
}