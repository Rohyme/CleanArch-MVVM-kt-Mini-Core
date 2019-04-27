package com.rohyme.kotlin_core.presentation.ui.screens.testScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rohyme.kotlin_core.domain.interactors.useCases.GetTestPostsUseCase
import com.rohyme.kotlin_core.presentation.ui.base.BaseViewModel
import com.rohyme.kotlin_core.presentation.utils.StateView
import com.rohyme.kotlin_core.presentation.utils.StateViewEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

/**
 *
 * @Auther Rohyme
 */
@ObsoleteCoroutinesApi
class TestViewModel(private val getPostsUseCase : GetTestPostsUseCase): BaseViewModel() {
    override val coroutineName: String
        get() = "TestViewModel"

    private val _postsEvent = MutableLiveData<StateViewEvent>()
    val getPostsEvent : LiveData<StateViewEvent>
    get() = _postsEvent

    fun fetchPosts() =  actor<StateView>(Dispatchers.Main,Channel.CONFLATED){
        getPostsUseCase.execute().consumeEach {
            _postsEvent.postValue(StateViewEvent(it))
        }
    }

}