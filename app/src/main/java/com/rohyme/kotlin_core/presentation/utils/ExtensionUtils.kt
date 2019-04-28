package com.rohyme.kotlin_core.presentation.utils

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.tripl3dev.prettystates.StatesConstants
import com.tripl3dev.prettystates.setState

/**
 *
 * @Auther Rohyme
 */


fun <T> LiveData<StateViewEvent>.stateObserve(
    owner: LifecycleOwner,
    containerView: View?=null,
    onLoading: () -> Unit,
    onSuccess: (t: T) -> Unit = {},
    onEmpty: () -> Unit = {},
    onError: (e: Exception, errorView: View?) -> Unit = { exep, view -> }
) {


    observe(owner, Observer { stateWrapper ->
        run {
            stateWrapper.getContentIfNotHandled()?.let {
                when (it) {
                    is StateView.Loading -> {
                        containerView?.setState(StatesConstants.LOADING_STATE)
                        onLoading()
                    }
                    is StateView.Success<*> -> {
                        containerView?.setState(StatesConstants.NORMAL_STATE)
                        onSuccess(it.data as T)
                    }
                    is StateView.Error -> {
                        val errorView = containerView?.setState(StatesConstants.ERROR_STATE)
                        onError(it.exception, errorView)
                    }
                    is StateView.Empty -> {
                        containerView?.setState(StatesConstants.EMPTY_STATE)
                        onEmpty()
                    }
                }
            }
        }
    })


}