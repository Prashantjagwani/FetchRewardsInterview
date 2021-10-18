package com.fetchrewards.example.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fetchrewards.example.R
import com.fetchrewards.example.model.Item

class ListRecyclerViewAdapter: RecyclerView.Adapter<ListViewHolder>() {

    private var itemList = emptyList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_item_layout, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(newItemList: List<Item>) {
        itemList = newItemList
        notifyDataSetChanged()
    }
}