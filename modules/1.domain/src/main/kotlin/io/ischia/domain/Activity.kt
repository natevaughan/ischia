package io.ischia.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Activity(
		val name: String,
		@ManyToOne
		val creator: User,
		@Id
		var slug: String
)