package com.selim.covid19pandemicstats.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CountryInfo (
    val _id:String="",
    val iso2:String="",
    val iso3:String="",
    val lat:String="",
    val long:String="",
    val flag:String=""
):Parcelable
