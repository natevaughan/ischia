package io.ischia


import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class StartupTasks {

	companion object {
		private val log by logger()
	}

	/**
	 * Here is an example task that will be run immediately after app startup.
	 * Specify any task dependencies in the method constructor.
	 */
	@Bean
	open fun runThisMethodOnStartup(config: TypedConfig) = ApplicationRunner {
		log.info(config.getString("ischia.welcome.message", "No welcome message found in app config"))
	}
}