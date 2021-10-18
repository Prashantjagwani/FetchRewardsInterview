package com.fetchrewards.example.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fetchrewards.example.R
import com.fetchrewards.example.model.Item

class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val listIdTextview: TextView = itemView.findViewById(R.id.list_id_input_textview)
    private val nameTextView: TextView = itemView.findViewById(R.id.name_input_textview)
    private val idTextView: TextView = itemView.findViewById(R.id.id_input_textview)

    fun bind(item: Item) {
        listIdTextview.text = item.listId.toString()
        nameTextView.text = item.name
        idTextView.text = item.id.toString()
    }
}