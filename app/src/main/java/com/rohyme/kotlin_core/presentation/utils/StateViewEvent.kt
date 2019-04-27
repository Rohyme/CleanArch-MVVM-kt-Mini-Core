package com.rohyme.kotlin_core.presentation.utils

/**
 *
 * @Auther Rohyme
 */
open class StateViewEvent(private val content: StateView) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): StateView? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): StateView = content
}