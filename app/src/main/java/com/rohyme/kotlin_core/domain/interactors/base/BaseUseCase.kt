package com.rohyme.kotlin_core.domain.interactors.base

import kotlinx.coroutines.Deferred

/**
 *
 * @Auther Rohyme
 */

abstract class BaseUseCase<PARAMS, RETURN>{

    abstract suspend fun buildUseCaseAsync(params: PARAMS?): Deferred<RETURN>

     suspend fun execute(params: PARAMS?=null) :Deferred<RETURN>{
        return buildUseCaseAsync(params)
    }
}