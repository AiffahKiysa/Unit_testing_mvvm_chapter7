package com.example.mvvm_car_chapter6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_car_chapter6.databinding.ItemContentBinding
import com.example.mvvm_car_chapter6.model.GetAllCarResponseItem

class MainAdapter(private val onItemClick: OnClickListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<GetAllCarResponseItem>() {
        override fun areItemsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: GetAllCarResponseItem,
            newItem: GetAllCarResponseItem
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<GetAllCarResponseItem>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemContentBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let { holder.bind(data) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GetAllCarResponseItem) {
            binding.apply {
                tvTitle.text = data.name
                tvPrice.text = data.price.toString()
                root.setOnClickListener {
                    onItemClick.onClickItem(data)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClickItem(data: GetAllCarResponseItem)
    }
}

