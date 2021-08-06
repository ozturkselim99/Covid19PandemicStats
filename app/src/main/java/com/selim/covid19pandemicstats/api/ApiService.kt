package com.selim.covid19pandemicstats.api

import com.selim.covid19pandemicstats.model.AllCountries
import com.selim.covid19pandemicstats.model.Countries
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("all")
    fun getAll(): Call<AllCountries>

    @GET("countries/{country}")
    fun getCountry(@Path("country") country:String):Call<Countries>

}