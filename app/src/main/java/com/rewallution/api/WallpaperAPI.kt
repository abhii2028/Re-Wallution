package com.rewallution.api

import android.app.Activity
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WallpaperAPI(activity: Activity, var callBack: ApiCalls.APICallback) {
    private var call: Call<JsonElement>? = null

    init {
        // create retrofit builder
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiCalls.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(activity.getOkHttpClient(true))
            .build()
    }
}