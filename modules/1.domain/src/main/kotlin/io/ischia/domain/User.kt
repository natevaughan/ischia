package io.ischia.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
	val email: String,
	@Id
	@GeneratedValue
	var id: Long? = null
)