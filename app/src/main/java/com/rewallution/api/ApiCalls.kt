package com.rewallution.api

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiCalls {
    companion object {
        // Url and credentials
        const val BaseUrl = "https://api.unsplash.com/"
        const val AccessKey = "5adafa6453f54e080a144bbd7ea840fdd306ea57419d2e8114c882b251e3b733"

        // Api
        const val PHOTOS = "photos"
        const val SUCCESS = 200
        const val FAILURE = 422

    }

    // Method to call API
    @GET(PHOTOS)
    fun getPhotos(@QueryMap params: Map<String, String>): Call<JsonElement>

    // interface which will give the result status and message
    interface APICallback {
        fun onSuccess(content: JsonElement? = null)
        fun onFailed(content: JsonElement? = null)
    }
}