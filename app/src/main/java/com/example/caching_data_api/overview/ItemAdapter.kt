package com.example.caching_data_api.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.caching_data_api.database.User
import com.example.caching_data_api.databinding.ListItemBinding

class ItemAdapter : androidx.recyclerview.widget.ListAdapter<User, ItemAdapter.SampleViewHolder>(DiffCallback){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class SampleViewHolder(private var binding: ListItemBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(userData: User){
                    binding.item = userData
                    binding.executePendingBindings()
                }
            }

    companion object DiffCallback: DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }
}