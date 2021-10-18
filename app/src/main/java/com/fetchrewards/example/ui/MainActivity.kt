package com.fetchrewards.example.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fetchrewards.example.main.MainApplication
import com.fetchrewards.example.main.MainViewModel
import com.fetchrewards.example.R

class MainActivity : AppCompatActivity() {

    private lateinit var mainRecyclerView : RecyclerView
    private lateinit var listRecyclerViewAdapter : ListRecyclerViewAdapter
    private lateinit var tagRecyclerView : RecyclerView
    private lateinit var tagRecyclerViewAdapter : TagRecyclerViewAdapter
    private val viewModel : MainViewModel by lazy {
        ViewModelProvider(this,
            MainViewModel.MainViewModelFactory((application as MainApplication).mainRepository)
        ).get(
            MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        loadData()
    }

    private fun init() {
        mainRecyclerView = findViewById(R.id.main_recyclerview)
        listRecyclerViewAdapter = ListRecyclerViewAdapter()

        tagRecyclerView = findViewById(R.id.tag_recyclerview)
        tagRecyclerViewAdapter = TagRecyclerViewAdapter()

        mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))
            adapter = listRecyclerViewAdapter

        }

        tagRecyclerView.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            tagRecyclerViewAdapter.tagClickListener = object : TagRecyclerViewAdapter.TagClickListener{
                override fun onTagClick(id: String) {
                        viewModel.getByListId(id).observe(this@MainActivity, Observer { itemList ->
                            listRecyclerViewAdapter.updateList(itemList)
                        })
                }

            }
            adapter = tagRecyclerViewAdapter


        }
    }

    private fun loadData() {
        viewModel.itemsInDatabase.observe(this, Observer { itemList ->
            listRecyclerViewAdapter.updateList(itemList)
        })

        viewModel.getIds().observe(this, Observer { itemList ->
            tagRecyclerViewAdapter.updateList(itemList)
        })
    }

}