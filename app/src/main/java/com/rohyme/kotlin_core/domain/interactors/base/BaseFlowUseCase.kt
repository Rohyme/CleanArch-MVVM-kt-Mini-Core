package com.rohyme.kotlin_core.domain.interactors.base

import com.rohyme.kotlin_core.presentation.utils.StateView
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseFlowUseCase<RETURN, PARAMS> {

    abstract suspend fun buildUseCaseAsync(params: PARAMS?): Deferred<RETURN>
    private var retryAction : (() -> Flow<StateView>)?=null

    fun execute(params : PARAMS?){
       retryAction = {
           flow {
               emit(StateView.Loading)
               try {
                   val data = buildUseCaseAsync(params).await()
                   emit(StateView.Success(data))
               } catch (e: Exception) {
                   e.printStackTrace()
                   emit(StateView.Error(e))
               }
           }
       }

    }

   fun retry(){
       retryAction?.invoke()
   }



}