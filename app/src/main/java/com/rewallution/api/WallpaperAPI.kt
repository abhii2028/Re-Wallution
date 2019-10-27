package com.rewallution.api

import com.google.gson.JsonElement
import com.rewallution.utility.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WallpaperAPI(
    private val page: Int = 1,
    private val limit: Int = 10,
    private val orderBy: String = Constant.LATEST,
    var callBack: ApiCalls.APICallback
) {
    private var call: Call<JsonElement>? = null

    init {
        // create retrofit builder
        val retrofit = Retrofit.Builder()
            .baseUrl(ApiCalls.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()

        // map the parameters with the server APIs parameter
        val params = HashMap<String, String>()
        params["client_id"] = ApiCalls.AccessKey
        params["page"] = page.toString()
        params["per_page"] = limit.toString()
        params["order_by"] = orderBy

        call = retrofit.create(ApiCalls::class.java).getPhotos(params)
    }

    // call the api and get response
    fun executeCall(): Call<JsonElement>? {
        call?.enqueue(object : Callback<JsonElement> {
            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                callBack.onFailed()
            }

            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                callBack.onSuccess(response.body())
            }

        })
        return call
    }
}