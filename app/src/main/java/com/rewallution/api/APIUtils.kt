package com.rewallution.api

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

fun getOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(90, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)

    httpClient.addInterceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder().method(original.method(), original.body()).build()
        chain.proceed(request)
    }

    return httpClient.build()
}