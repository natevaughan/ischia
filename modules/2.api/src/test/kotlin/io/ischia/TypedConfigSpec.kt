package io.ischia

import org.junit.Assert.assertEquals
import org.junit.Test

class TypedConfigSpec {

	private val typedConfig = object: TypedConfig {
		override fun getString(key: String): String {
			throw NotImplementedError()
		}

		override fun getBoolean(key: String): Boolean {
			throw NotImplementedError()
		}

		override fun getLong(key: String): Long {
			throw NotImplementedError()
		}

	}

	@Test
	fun testBooleanDefault() {
		assertEquals("It should provide default booleans", true, typedConfig.getBoolean("some.key", true))
	}

	@Test
	fun testStringDefault() {
		val stringValue = "foo"
		assertEquals("It should provide default strings", stringValue, typedConfig.getString("some.key", stringValue))
	}

	@Test
	fun testLongDefault() {
		val one = 1L
		assertEquals("It should provide default longs", one, typedConfig.getLong("some.key", one))
	}
}
