package io.ischia.domain

interface ActivityDAO {
	fun findAllByCreator(creator: User): List<Message>
}