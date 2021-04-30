package com.rapps.foodappsradyalabs.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rapps.foodappsradyalabs.api.RetrofitInstance
import com.rapps.foodappsradyalabs.data.FoodResponse
import com.rapps.foodappsradyalabs.utils.ViewModelState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel() : ViewModel() {

    private val _listFoods = MutableLiveData<ViewModelState<List<FoodResponse>>>()
    val listfoods: LiveData<ViewModelState<List<FoodResponse>>> get() = _listFoods

    fun getFoods() {
        _listFoods.value = ViewModelState.Loading()
        RetrofitInstance.api.getFoods().enqueue(object : Callback<List<FoodResponse>> {
            override fun onResponse(
                call: Call<List<FoodResponse>>,
                response: Response<List<FoodResponse>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {data ->
                        _listFoods.value = ViewModelState.Success(data)
                    }
                }
            }

            override fun onFailure(call: Call<List<FoodResponse>>, t: Throwable) {
                _listFoods.value = ViewModelState.Error(toString())
            }

        })
    }
}



