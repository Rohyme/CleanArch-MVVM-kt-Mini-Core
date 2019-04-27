package com.rohyme.kotlin_core.presentation.ui.screens.testScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.rohyme.kotlin_core.R
import com.rohyme.kotlin_core.presentation.utils.StateView
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestScreen : AppCompatActivity() {

    @ObsoleteCoroutinesApi
    val testVM : TestViewModel by viewModel()

    @ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_screen)
        testVM.fetchPosts()
        testVM.getPostsEvent.observe(this, Observer {stateWrapper ->
            stateWrapper.getContentIfNotHandled()?.let {
                when(it){
                    is StateView.Success<*> ->{
                        Log.e("Test Screen","success ${it.data}")
                    }
                    is StateView.Error ->{
                        Log.e("Test Screen","error ${it.exception.message}")
                    }
                    is StateView.Loading ->{
                        Log.e("Test Screen","Loading")
                    }
                    is StateView.Empty ->{

                    }
                }
            }
        })
    }
}
