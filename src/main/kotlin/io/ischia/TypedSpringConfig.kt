package io.ischia

import org.springframework.core.env.Environment
import javax.inject.Named

/**
 * Reads properties in e.g. application.properties
 * and casts them to an expected value
 */
@Named("config")
class TypedSpringConfig(val env: Environment): TypedConfig {

	companion object {
		private val log by logger()
	}

	override fun getString(key: String): String {
		val prop = env.getProperty(key)
		if (prop != null) {
			return prop
		}
		throw NullPointerException("String not found for property key $key")
	}

	override fun getBoolean(key: String): Boolean {
		val prop = env.getProperty(key)
		if (prop != null) {
			when (prop.toLowerCase()) {
				"true" -> return true
				"false" -> return false
			}
		}
		throw NullPointerException("Boolean not found for property key $key")
	}

	override fun getLong(key: String): Long {
		val prop = env.getProperty(key)
		if (prop != null) {
			try {
				return prop.toLong()
			} catch (e: NumberFormatException) {
				log.error("Failed to parse long for key ${key}: value was ${prop}")
			}
		}
		throw NullPointerException("Long not found for property key $key")
	}

	override fun getString(key: String, defaultVal: String): String {
		val prop = env.getProperty(key)
		if (prop != null) {
			return prop
		}
		return defaultVal
	}

	override fun getBoolean(key: String, defaultVal: Boolean): Boolean {
		val prop = env.getProperty(key)
		if (prop != null) {
			when (prop.toLowerCase()) {
				"true" -> return true
				"false" -> return false
			}
		}
		return defaultVal
	}

	override fun getLong(key: String, defaultVal: Long): Long {
		val prop = env.getProperty(key)
		if (prop != null) {
			try {
				return prop.toLong()
			} catch (e: NumberFormatException) {
				log.error("Failed to parse long for key ${key}: value was ${prop}")
			}
		}
		return defaultVal
	}
}