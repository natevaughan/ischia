package io.ischia.domain

/**
 * Example Data Access Object (DAO) interface. Note this module does not
 * use Spring or Hibernate and allows data access using pure Kotlin.
 *
 * If method names follow hibernate naming conventions, these methods will
 * automagically get wired up at runtime with hibernate implementations.
 *
 * See io.ischia.repository.Repositories to see where the wiring magic happens.
 */
interface ActivityDAO {
	fun findAllByCreator(creator: User): List<Activity>
}