package com.rohyme.kotlin_core.presentation.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rohyme.kotlin_core.domain.interactors.base.BaseUseCase
import com.rohyme.kotlin_core.presentation.utils.StateView
import com.rohyme.kotlin_core.presentation.utils.StateViewEvent
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import org.koin.core.KoinComponent
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

/**
 *
 * @Auther Rohyme
 */

abstract class BaseViewModel : ViewModel(), CoroutineScope, KoinComponent {

    override val coroutineContext: CoroutineContext =
        SupervisorJob() +
                Dispatchers.IO +
                CoroutineName(getClassName()) +
                CoroutineExceptionHandler { _, throwable -> Log.e("${getClassName()}",throwable.message) }

    abstract val coroutineName: String
    fun getClassName(): String =javaClass.simpleName
    /**
     * Load data the background
     */
    fun <P,T> MutableLiveData<StateViewEvent>.executeWithState(useCase: BaseUseCase<P ,T>,params : P?=null, dispatcher: CoroutineDispatcher = Dispatchers.IO){
        launch {
        withContext(dispatcher) {
            postValue(StateViewEvent(StateView.Loading))
            try {
                val data = useCase.execute(params).await()
                postValue(StateViewEvent(StateView.Success(data)))
            }catch (e :Exception){
                postValue(StateViewEvent(StateView.Error(e)))
            }
        }
    }
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
    }
}