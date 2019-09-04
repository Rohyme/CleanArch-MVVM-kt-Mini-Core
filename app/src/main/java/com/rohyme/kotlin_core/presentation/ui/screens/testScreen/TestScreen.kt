package com.rohyme.kotlin_core.presentation.ui.screens.testScreen

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.rohyme.kotlin_core.R
import com.rohyme.kotlin_core.data.remote.requests.PostResponse
import com.rohyme.kotlin_core.presentation.utils.stateObserve
import kotlinx.android.synthetic.main.activity_test_screen.*
import kotlinx.coroutines.ObsoleteCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

class TestScreen : AppCompatActivity() {

    val testVM : TestViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_screen)
        testVM.fetchPosts()

        testVM.getPostsEvent.stateObserve<List<PostResponse>>(owner =  this,
            containerView = container
            ,onSuccess = {
            Log.e("Test Screen","success $it")
        },onLoading = {
            Log.e("Test Screen","Loading")

        } ,onError = {ex , view->
                view?.findViewById<TextView>(R.id.textError)?.text = ex.message
            view?.setOnClickListener {
                testVM.retryGettingPosts()
            }
            Log.e("Test Screen","error ${ex.message}")
        })
    }
}
