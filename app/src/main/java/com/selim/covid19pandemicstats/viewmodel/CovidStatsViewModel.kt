package com.selim.covid19pandemicstats.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selim.covid19pandemicstats.api.ApiClient
import com.selim.covid19pandemicstats.model.AllCountries
import com.selim.covid19pandemicstats.model.Countries
import retrofit2.Call
import retrofit2.Response


class CovidStatsViewModel:ViewModel() {

    val allCountries:MutableLiveData<AllCountries> = MutableLiveData()
    val _country:MutableLiveData<Countries> = MutableLiveData()

    fun getAll()
    {
        ApiClient.getApiService().getAll().enqueue(object : retrofit2.Callback<AllCountries> {
            override fun onResponse(call: Call<AllCountries>, response: Response<AllCountries>) {
               response.body()?.let {
                   allCountries.value=it
                }
            }
            override fun onFailure(call: Call<AllCountries>, t: Throwable) {
                Log.d("Response",t.message.toString())
            }

        })
    }

    fun getCountry(country:String)
    {
       ApiClient.getApiService().getCountry(country).enqueue(object :retrofit2.Callback<Countries>{
           override fun onResponse(call: Call<Countries>, response: Response<Countries>) {
               response.body()?.let {
                   _country.value=it
               }
           }

           override fun onFailure(call: Call<Countries>, t: Throwable) {
               Log.d("Response",t.message.toString())
           }

       })
    }

}