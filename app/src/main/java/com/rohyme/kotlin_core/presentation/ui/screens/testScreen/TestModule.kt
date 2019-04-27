package com.rohyme.kotlin_core.presentation.ui.screens.testScreen

import com.rohyme.kotlin_core.data.repositories.TestRepository
import com.rohyme.kotlin_core.domain.interactors.useCases.GetTestPostsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * @Auther Rohyme
 */

val testModule = module {
    factory { TestRepository(get()) }
    factory { GetTestPostsUseCase(get()) }
    viewModel { TestViewModel(get()) }
}