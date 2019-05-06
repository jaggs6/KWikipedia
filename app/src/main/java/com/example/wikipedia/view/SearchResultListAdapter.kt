package com.example.wikipedia.view

import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.wikipedia.R
import com.example.wikipedia.inflate
import com.example.wikipedia.model.Search.SearchItem

class SearchResultListAdapter(private val searchList: List<SearchItem>) : RecyclerView.Adapter<SearchResultListAdapter.SearchHolder>() {
    class SearchHolder(v: View): RecyclerView.ViewHolder(v) {
        val twoLineListItem: View = v
        var searchItem: SearchItem? = null

        @Suppress("DEPRECATION")
        fun bindSearch(item: SearchItem) {
            this.searchItem = item
            twoLineListItem.findViewById<TextView>(R.id.textView1).text = Html.fromHtml(item.title)
            twoLineListItem.findViewById<TextView>(R.id.textView2).text = Html.fromHtml(item.snippet)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): SearchHolder {
        return SearchHolder(parent.inflate(R.layout.recycler_view_item, false))
    }

    override fun onBindViewHolder(viewHolder: SearchHolder, index: Int) {
        viewHolder.bindSearch(searchList[index])
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}
