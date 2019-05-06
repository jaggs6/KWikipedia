package com.example.wikipedia.controller

import android.annotation.SuppressLint
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import com.example.wikipedia.hideKeyboard
import com.example.wikipedia.model.Search
import com.example.wikipedia.model.WikiApiService
import com.example.wikipedia.view.MainActivity
import com.example.wikipedia.view.SearchResultListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*


class SearchController(private val activity: MainActivity) {

    init {
        activity.searchButton.setOnClickListener {
            if (activity.searchBox.text.toString().isNotEmpty()) {
                beginSearch()
            }
        }
        activity.searchBox.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                beginSearch()
            }
            false
        }
    }

    private val wikiApiServe by lazy {
        WikiApiService.create()
    }
    var disposable: Disposable? = null

    private fun beginSearch() {
        activity.hideKeyboard()
        disposable =
            wikiApiServe.api("query", "json", "search", activity.searchBox.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> showResult(result.query.searchinfo.totalhits, result.query.search) },
                    { error -> showError(error.message) }
                )
    }

    private fun showError(message: String?) {
        activity.searchResult.text = message
    }

    @SuppressLint("SetTextI18n")
    private fun showResult(totalhits: Int, searchList: List<Search.SearchItem>) {
        activity.searchResult.text = "$totalhits results found"
        activity.searchResultList.layoutManager = LinearLayoutManager(activity)
        activity.searchResultList.adapter = SearchResultListAdapter(searchList)
        (activity.searchResultList.adapter as SearchResultListAdapter).notifyDataSetChanged()
    }
}