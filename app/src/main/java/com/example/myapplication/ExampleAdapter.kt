package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.GridItemBinding

class ExampleAdapter(val callback: (model: ExampleModel) -> Unit) :
    ListAdapter<ExampleModel, ExampleAdapter.ExampleViewHolder>(object :
        DiffUtil.ItemCallback<ExampleModel>() {
        override fun areItemsTheSame(
            oldItem: ExampleModel,
            newItem: ExampleModel
        ) = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ExampleModel,
            newItem: ExampleModel
        ) = oldItem == newItem
    }) {


    inner class ExampleViewHolder(
        val callback: (model: ExampleModel) -> Unit,
        private val binding: GridItemBinding
    ) : ViewHolder(binding.root) {
        fun binding(model: ExampleModel) {
            binding.title.setOnClickListener {
                callback.invoke(model)
            }
            binding.title.text = model.title
            Glide.with(binding.root.context)
                .load(model.imageUrl)
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = GridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExampleViewHolder(callback, view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.binding(getItem(position))
    }
}

data class ExampleModel(val title: String, val id: Int, val imageUrl: String)