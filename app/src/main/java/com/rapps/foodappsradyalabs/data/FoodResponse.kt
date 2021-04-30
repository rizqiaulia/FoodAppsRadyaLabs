package com.rapps.foodappsradyalabs.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodResponse(
    val name:String,
    val image:String,
    val desc:String
):Parcelable
