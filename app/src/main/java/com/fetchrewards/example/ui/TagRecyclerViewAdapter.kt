package com.fetchrewards.example.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fetchrewards.example.R
import com.fetchrewards.example.model.Item

class TagRecyclerViewAdapter: RecyclerView.Adapter<TagViewHolder>() {

    private var itemList = emptyList<Item>()
    private var selectedId = ""
    private var selectedPos = -1

    var tagClickListener: TagClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tag_row_item_layout, parent, false)
        return TagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
        if (selectedId == itemList[position].listId.toString()){
            holder.setSelected(true)
        } else {
            holder.setSelected(false)
        }
        holder.itemView.setOnClickListener {
            if (selectedId != itemList[position].listId.toString()){
                selectedId = itemList[position].listId.toString()
                if (selectedPos!=-1)
                notifyItemChanged(selectedPos)
                selectedPos = position
                holder.setSelected(true)
                tagClickListener?.onTagClick(itemList[position].listId.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun updateList(newItemList: List<Item>) {
        itemList = newItemList
        notifyDataSetChanged()
    }

    interface TagClickListener {
        fun onTagClick(id:String)
    }
}