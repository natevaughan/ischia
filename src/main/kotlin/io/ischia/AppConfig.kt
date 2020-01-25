package io.ischia

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class AppConfig {

	@Bean
	open fun objectMapper(): ObjectMapper {
		val objectMapper = ObjectMapper()
		objectMapper.registerModule(KotlinModule())
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
		return objectMapper
	}
}