package io.ischia

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * source https://stackoverflow.com/a/34462577
 */
// unwrap companion class to enclosing class given a Java Class
fun <T : Any> unwrapCompanionClass(ofClass: Class<T>): Class<*> {
	return ofClass.enclosingClass?.takeIf {
		ofClass.kotlin.isCompanion
	} ?: ofClass
}
fun <R : Any> R.logger(): Lazy<Logger> {
	return lazy { LoggerFactory.getLogger(unwrapCompanionClass(this.javaClass).name) }
}