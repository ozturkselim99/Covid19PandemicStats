package com.selim.covid19pandemicstats.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Countries (
        var countryInfo: CountryInfo=CountryInfo(),
        val cases: String="",
        val todayCases: String="",
        val deaths: String="",
        val todayDeaths: String="",
        val recovered: String="",
        val todayRecovered:String="",
        val active: String="",
        val critical: String="",
        val casesPerOneMillion: String="",
        val deathsPerOneMillion: String="",
        val tests: String="",
        val testsPerOneMillion: String="",
        val population:String="",
        val continent:String="",
        val oneCasePerPeople: String="",
        val oneDeathPerPeople: String="",
        val oneTestPerPeople: String="",
        val activePerOneMillion: String="",
        val recoveredPerOneMillion: String="",
        val criticalPerOneMillion: String=""
):Parcelable
