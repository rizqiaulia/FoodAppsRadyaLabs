package com.rapps.foodappsradyalabs.api

import com.rapps.foodappsradyalabs.data.FoodResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FoodApi {

    @GET("foods")
    fun getFoods():Call<List<FoodResponse>>
}