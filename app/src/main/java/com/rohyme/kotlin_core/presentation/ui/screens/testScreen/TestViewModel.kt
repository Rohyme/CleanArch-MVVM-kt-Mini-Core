package com.rohyme.kotlin_core.presentation.ui.screens.testScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rohyme.kotlin_core.domain.interactors.useCases.GetTestPostsUseCase
import com.rohyme.kotlin_core.presentation.ui.base.BaseViewModel
import com.rohyme.kotlin_core.presentation.utils.StateViewEvent

/**
 *git
 * @Auther Rohyme
 */
class TestViewModel(private val getPostsUseCase : GetTestPostsUseCase): BaseViewModel() {
    override val coroutineName: String
        get() = "TestViewModel"

    private val _postsEvent = MutableLiveData<StateViewEvent>()
    val getPostsEvent : LiveData<StateViewEvent>
    get() = _postsEvent

     fun fetchPosts(){
         _postsEvent.executeWithState(getPostsUseCase)
     }
    fun retryGettingPosts() {
        fetchPosts()
    }
}