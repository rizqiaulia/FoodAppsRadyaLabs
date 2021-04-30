package com.rapps.foodappsradyalabs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rapps.foodappsradyalabs.R
import com.rapps.foodappsradyalabs.data.FoodResponse
import com.rapps.foodappsradyalabs.databinding.ItemFoodBinding
import com.rapps.foodappsradyalabs.listener.OnItemClick
import com.rapps.foodappsradyalabs.utils.GlideApp
import com.rapps.foodappsradyalabs.utils.getProgressDrawable
import com.rapps.foodappsradyalabs.utils.loadImage

class FoodListRVAdapter : ListAdapter<FoodResponse, FoodListRVAdapter.ViewHolder>(DiffCallback()) {

    private var onClickItem: OnItemClick? = null

    fun onItemClick(onClickItem: OnItemClick) {
        this.onClickItem = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFoodBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentList = getItem(position)
        holder.bind(currentList)

        holder.itemBinding.root.setOnClickListener {
            onClickItem?.onClick(position)
        }
    }

    class ViewHolder( val itemBinding: ItemFoodBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: FoodResponse) {
            itemBinding.apply {
                tvFoodTitle.text = item.name
                ivFoodImage.loadImage(item.image, getProgressDrawable(root.context))
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<FoodResponse>() {
        override fun areItemsTheSame(
            oldItem: FoodResponse,
            newItem: FoodResponse
        ) = oldItem.hashCode() == newItem.hashCode()

        override fun areContentsTheSame(
            oldItem: FoodResponse,
            newItem: FoodResponse
        ) = oldItem == newItem

    }
}