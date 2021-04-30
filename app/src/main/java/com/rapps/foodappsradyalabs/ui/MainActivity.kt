package com.rapps.foodappsradyalabs.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.rapps.foodappsradyalabs.data.FoodResponse
import com.rapps.foodappsradyalabs.databinding.ActivityMainBinding
import com.rapps.foodappsradyalabs.listener.OnItemClick
import com.rapps.foodappsradyalabs.ui.adapter.FoodListRVAdapter
import com.rapps.foodappsradyalabs.ui.detail.DetailActivity
import com.rapps.foodappsradyalabs.utils.ViewModelState

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    lateinit var mainBinding: ActivityMainBinding
    lateinit var adapterRV : FoodListRVAdapter
    var listFood = ArrayList<FoodResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        viewModel.getFoods()

        initViews()
        observeViewModels()
        initListener()
    }

    private fun initViews() {
        adapterRV = FoodListRVAdapter()
        mainBinding.rvListFood.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterRV
        }
    }

    private fun initListener() {
        adapterRV.onItemClick(object :OnItemClick{
            override fun onClick(position: Int) {
               val intent = Intent(this@MainActivity,DetailActivity::class.java)
                intent.putExtra("detail",listFood[position])
                startActivity(intent)
            }
        })
    }

    private fun observeViewModels() {
        viewModel.listfoods.observe(this, { state ->
            when (state) {
                is ViewModelState.Error -> {
                    mainBinding.pbLoading.isVisible = false
                }
                is ViewModelState.Loading -> {
                    mainBinding.pbLoading.isVisible = true
                }
                is ViewModelState.Success -> {
                    mainBinding.pbLoading.isVisible = false
                    state.data?.let {data ->
                        listFood.clear()
                        listFood.addAll(data)
                        adapterRV.submitList(listFood)
                    }
                }
            }
        })
    }

}