package com.bogatovnikita.babosiki.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bogatovnikita.babosiki.databinding.ItemCurrencyBinding
import com.bogatovnikita.babosiki.models.CurrencyItem

class PopularCurrencyAdapter(private val listener: (String) -> Unit) :
    ListAdapter<CurrencyItem, PopularCurrencyAdapter.PopularCurrencyViewHolder>(
        CurrencyDiffCallback()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return PopularCurrencyViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PopularCurrencyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PopularCurrencyViewHolder(
        private val binding: ItemCurrencyBinding,
        private val listener: (String) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CurrencyItem) {
            binding.name.text = item.key
            binding.count.text = item.value.toString()
            binding.favouriteBtn.setOnClickListener {
                listener(item.key)
            }
        }
    }

    class CurrencyDiffCallback : DiffUtil.ItemCallback<CurrencyItem>() {
        override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
            return oldItem.value == newItem.value
        }

        override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
            return oldItem == newItem
        }
    }
}