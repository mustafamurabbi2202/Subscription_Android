package com.example.netflix_subscription.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix_subscription.data.App
import com.example.netflix_subscription.data.Category
import com.example.netflix_subscription.databinding.ItemAppSelectionBinding
import com.example.netflix_subscription.databinding.ItemCategorySelectionBinding
import com.example.netflix_subscription.databinding.ItemFrequencySelectionBinding

class AppSelectionAdapter(
    private val onAppSelected: (App) -> Unit
) : ListAdapter<App, AppSelectionAdapter.ViewHolder>(AppDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAppSelectionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemAppSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(app: App) {
            binding.apply {
                tvAppName.text = app.name
                ivAppIcon.setImageResource(app.icon)

                root.setOnClickListener {
                    onAppSelected(app)
                }
            }
        }
    }

    class AppDiffCallback : DiffUtil.ItemCallback<App>() {
        override fun areItemsTheSame(oldItem: App, newItem: App): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: App, newItem: App): Boolean {
            return oldItem == newItem
        }
    }
}

// CategorySelectionAdapter.kt
class CategorySelectionAdapter(
    private val onCategorySelected: (Category) -> Unit
) : ListAdapter<Category, CategorySelectionAdapter.ViewHolder>(CategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategorySelectionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCategorySelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                tvCategoryName.text = category.name
                ivCategoryIcon.setImageResource(category.icon)

                root.setOnClickListener {
                    onCategorySelected(category)
                }
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}

// FrequencySelectionAdapter.kt
class FrequencySelectionAdapter(
    private val onFrequencySelected: (String) -> Unit
) : ListAdapter<String, FrequencySelectionAdapter.ViewHolder>(FrequencyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFrequencySelectionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemFrequencySelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(frequency: String) {
            binding.apply {
                tvFrequency.text = frequency

                root.setOnClickListener {
                    onFrequencySelected(frequency)
                }
            }
        }
    }

    class FrequencyDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}