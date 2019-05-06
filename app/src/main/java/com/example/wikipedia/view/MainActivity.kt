package com.example.wikipedia.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.wikipedia.R
import com.example.wikipedia.controller.SearchController

class MainActivity : AppCompatActivity() {

    private lateinit var searchController: SearchController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchController = SearchController(this)
    }

    override fun onPause() {
        super.onPause()
        searchController.disposable?.dispose()
    }
}
