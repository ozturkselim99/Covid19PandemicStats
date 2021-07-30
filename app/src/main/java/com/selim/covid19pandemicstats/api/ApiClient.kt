package com.selim.covid19pandemicstats.api

import com.selim.covid19pandemicstats.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun getApiService():ApiService{
        val retrofitBuilder=Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
        return retrofitBuilder
    }

}