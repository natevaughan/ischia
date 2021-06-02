package io.ischia.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * Example entity class
 */
@Entity
class User(
	val email: String,
	@Id
	@GeneratedValue
	var id: Long? = null
)