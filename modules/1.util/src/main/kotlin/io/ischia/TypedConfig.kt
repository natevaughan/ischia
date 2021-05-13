package io.ischia

/**
 * Vanilla interface that provides type-safe access to config values
 *
 * @see TypedSpringConfig for a Spring implementation
 */
interface TypedConfig {

	/**
	 * Get a String config value
	 * @throws NullPointerException if key does not exist
	 */
	fun getString(key: String): String

	/**
	 * Get a Boolean config value
	 * @throws NullPointerException if key does not exist
	 */
	fun getBoolean(key: String): Boolean

	/**
	 * Get a Long config value
	 * @throws NullPointerException if key does not exist
	 */
	fun getLong(key: String): Long

	/**
	 * Get a String or the default value
	 */
	fun getString(key: String, defaultVal: String): String {
		return defaultVal
	}

	/**
	 * Get a Boolean or the default value
	 */
	fun getBoolean(key: String, defaultVal: Boolean): Boolean {
		return defaultVal
	}

	/**
	 * Get a Long or the default value
	 */
	fun getLong(key: String, defaultVal: Long): Long {
		return defaultVal
	}
}
