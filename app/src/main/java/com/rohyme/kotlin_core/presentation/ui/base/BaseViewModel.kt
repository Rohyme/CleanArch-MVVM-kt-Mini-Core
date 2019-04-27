package com.rohyme.kotlin_core.presentation.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import kotlin.coroutines.CoroutineContext

/**
 *
 * @Auther Rohyme
 */

abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent {

    private val viewModelJob = Job()
    override val coroutineContext: CoroutineContext
        get() = CoroutineName(coroutineName) + CoroutineExceptionHandler { _, e ->

            println("Context Name : $coroutineName: ${e.printStackTrace()}")
        } + Dispatchers.Main + viewModelJob

    abstract val coroutineName: String

    /**
     * Load data the background
     */
    fun launchBg(block: suspend () -> Unit) = launch {
        // show Loading
        withContext(Dispatchers.IO) {
            block()
        }
        // hide Loading
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}