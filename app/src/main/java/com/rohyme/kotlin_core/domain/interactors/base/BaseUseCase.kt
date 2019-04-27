package com.rohyme.kotlin_core.domain.interactors.base

import android.util.Log
import com.rohyme.kotlin_core.presentation.utils.StateView
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.CoroutineContext

/**
 *
 * @Auther Rohyme
 */

@ExperimentalCoroutinesApi
abstract class BaseUseCase<PARAMS, RETURN>(private var dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    CoroutineScope {
    private val currentJob: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = CoroutineName(getClassName()) + CoroutineExceptionHandler { _, e ->
            Log.e("${getClassName()} error", e.message)
        } + dispatcher + currentJob
    private var currentParams: PARAMS? = null
    private fun getClassName(): String = javaClass.name ?: ""


    abstract suspend fun buildUseCaseAsync(params: PARAMS?): Deferred<RETURN>

     fun execute(params: PARAMS?=null): ReceiveChannel<StateView> {
        return produce {
            currentParams = params
            send(StateView.Loading)
            try {
                val returnData = buildUseCaseAsync(params).await()
                send(StateView.Success(returnData))
            } catch (e: Exception) {
                send(StateView.Error(e))
            }
        }
    }


    fun unsubscribe() {
        coroutineContext.cancelChildren()
    }

    fun retry() {
        launch {
            execute(currentParams)
        }
    }
}