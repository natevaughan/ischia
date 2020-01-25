package io.ischia

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class App

fun main(args: Array<String>) {
	SpringApplication.run(App::class.java, *args)
}
