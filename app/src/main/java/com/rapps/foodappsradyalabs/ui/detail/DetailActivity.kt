package com.rapps.foodappsradyalabs.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rapps.foodappsradyalabs.data.FoodResponse
import com.rapps.foodappsradyalabs.databinding.ActivityDetailBinding
import com.rapps.foodappsradyalabs.utils.getProgressDrawable
import com.rapps.foodappsradyalabs.utils.loadImage

class DetailActivity : AppCompatActivity() {

    lateinit var foodDetail: FoodResponse

    lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        foodDetail = intent.getParcelableExtra("detail")!!

        binding.tvFoodTitle.text = foodDetail.name
        binding.tvDesc.text = foodDetail.desc
        binding.ivDetail.loadImage(foodDetail.image, getProgressDrawable(this@DetailActivity))

    }
}