package com.example.myapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewbinding.ViewBinding
import com.example.myapplication.databinding.GridItemBinding
import com.example.myapplication.databinding.TitleItemBinding

class NewAdapter :
    ListAdapter<BaseDataModel, NewAdapter.BaseViewHolder<ViewBinding, BaseDataModel>>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BaseDataModel>() {
            override fun areItemsTheSame(
                oldItem: BaseDataModel,
                newItem: BaseDataModel
            ) = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: BaseDataModel,
                newItem: BaseDataModel
            ) = oldItem == newItem
        }
    }

    inner class TitleViewHolder(binding: TitleItemBinding) :
        BaseViewHolder<TitleItemBinding, BaseDataModel.TitleModel>(binding) {
        override fun bind(model: BaseDataModel.TitleModel, position: Int) {
            binding.title.text = model.title
        }
    }

    inner class NewViewHolder(binding: GridItemBinding) :
        BaseViewHolder<GridItemBinding, BaseDataModel.RecyclerViewModel>(binding) {
        override fun bind(model: BaseDataModel.RecyclerViewModel, position: Int) {
            binding.title.text = model.title
            binding.description.text = model.description
            binding.number.text = model.number.toString()

            if (position % 2 == 0) {
                binding.root.setBackgroundColor(Color.RED)
            } else {
                binding.root.setBackgroundColor(Color.GREEN)
            }
        }
    }

    open inner class BaseViewHolder<T : ViewBinding, M : BaseDataModel>(val binding: T) :
        ViewHolder(binding.root) {
        open fun bind(model: M, position: Int) {
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is BaseDataModel.RecyclerViewModel -> {
                R.layout.grid_item
            }
            is BaseDataModel.TitleModel -> {
                R.layout.title_item
            }
        }

    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewBinding, BaseDataModel>,
        position: Int
    ) {
        holder.bind(getItem(position), position)
        println(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewBinding, BaseDataModel> {

        return when (viewType) {
            R.layout.grid_item -> {
                NewViewHolder(
                    GridItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ) as BaseViewHolder<ViewBinding, BaseDataModel>
            }
            R.layout.title_item -> {
                TitleViewHolder(
                    TitleItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ) as BaseViewHolder<ViewBinding, BaseDataModel>
            }
            else -> {
                throw  IllegalStateException()
            }
        }


    }
}

sealed class BaseDataModel(open val title: String) {
    data class RecyclerViewModel(
        override val title: String,
        val description: String,
        val number: Int
    ) : BaseDataModel(title)

    data class TitleModel(
        override val title: String
    ) : BaseDataModel(title)
}

