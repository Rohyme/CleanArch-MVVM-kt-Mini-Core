package com.rohyme.kotlin_core.presentation.di.network

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rohyme.kotlin_core.data.dataUtils.SharedPreferenceUtil
import com.rohyme.kotlin_core.data.remote.ApiService
import com.rohyme.kotlin_core.presentation.utils.BASE_URL
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 *
 * @Auther Rohyme
 */


val networkModule = module {
    single { providesHttpCache(androidApplication()) }
    single { providesGson() }
    single { SharedPreferenceUtil() }
    single { provideOkhttpClient(get(), get()) }
    single {
        providesRetrofit(
            get(),
            get()
        )
    }
    single { providesApiService(get()) }
}


fun providesHttpCache(appContext: Context): Cache? {
    val cacheSize = 10 * 1024 * 1024
    var cache: Cache? = null
    try {
        val myDir = File(appContext.cacheDir, "response")
        myDir.mkdir()
        cache = Cache(myDir, cacheSize.toLong())
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return cache
}


fun providesGson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .serializeNulls()
    return gsonBuilder.create()
}

fun provideOkhttpClient(cache: Cache?, pref: SharedPreferenceUtil): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .cache(cache)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}


fun providesRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}


fun providesApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
