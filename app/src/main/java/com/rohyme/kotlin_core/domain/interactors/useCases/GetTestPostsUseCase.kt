package com.rohyme.kotlin_core.domain.interactors.useCases

import com.rohyme.kotlin_core.data.remote.requests.PostResponse
import com.rohyme.kotlin_core.data.repositories.TestRepository
import com.rohyme.kotlin_core.domain.interactors.base.BaseUseCase
import kotlinx.coroutines.Deferred

/**
 *
 * @Auther Rohyme
 */
class GetTestPostsUseCase(private val testRepository: TestRepository): BaseUseCase<Unit, List<PostResponse>>() {

    override suspend fun buildUseCaseAsync(params: Unit?): Deferred<List<PostResponse>> {
        return testRepository.getPosts()
    }
}