package com.github.mahmoud_ashraf.filterabledialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.search_item_view.view.*


class SearchAdapter : ListAdapter<SearchUiModel, SearchAdapter.SearchAdapterViewHolder>(SearchDiffCallback()) {

    var onSearchItemSelected: ((position: Int, searchListItem: SearchUiModel) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item_view, parent, false)
        return SearchAdapterViewHolder(view)
    }



    override fun onBindViewHolder(holder: SearchAdapterViewHolder, position: Int) {
        val searchListItem = getItem(position)
        holder.bind(searchListItem, onSearchItemSelected, position)
    }

    class SearchAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(searchListItem: SearchUiModel, onSearchItemSelected: ((position: Int, searchListItem: SearchUiModel) -> Unit)?, position: Int) {
            itemView.title_text.text = searchListItem.title
            itemView.title_text.setOnClickListener {
                onSearchItemSelected?.invoke(position,searchListItem)
            }
        }
    }
}