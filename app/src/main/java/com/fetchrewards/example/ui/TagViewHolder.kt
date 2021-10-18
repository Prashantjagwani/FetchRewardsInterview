package com.fetchrewards.example.ui

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fetchrewards.example.R
import com.fetchrewards.example.model.Item

class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val listIdTextview: TextView = itemView.findViewById(R.id.list_id_input_textview)

    fun bind(item: Item) {
        if (item.listId == -1)
            listIdTextview.text = "All"
        else
            listIdTextview.text = item.listId.toString()
    }

    fun setSelected(selected: Boolean) {
        if (selected)
            listIdTextview.setTextColor(Color.RED)
        else
            listIdTextview.setTextColor(Color.BLACK)
    }
}