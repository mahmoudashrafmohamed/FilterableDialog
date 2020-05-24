package com.github.mahmoud_ashraf.filterabledialog

import androidx.recyclerview.widget.DiffUtil

class SearchDiffCallback : DiffUtil.ItemCallback<SearchUiModel>() {
    override fun areItemsTheSame(
            oldItem: SearchUiModel,
            newItem: SearchUiModel
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
            oldItem: SearchUiModel,
            newItem: SearchUiModel
    ): Boolean {
        return oldItem == newItem
    }


}