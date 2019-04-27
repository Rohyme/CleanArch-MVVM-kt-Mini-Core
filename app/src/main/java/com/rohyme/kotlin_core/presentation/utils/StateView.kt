package com.rohyme.kotlin_core.presentation.utils

/**
 *
 * @Auther Rohyme
 */

sealed class StateView {
    data class Success<T>(val data: T) : StateView()
    data class Error(val exception: Exception) : StateView()
    object Loading : StateView()
    object Empty : StateView()
}
