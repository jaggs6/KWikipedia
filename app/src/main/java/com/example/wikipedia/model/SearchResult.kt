package com.example.wikipedia.model

object Search {
    data class Result(val query: Query)
    data class Query(val searchinfo: SearchInfo, val search: List<SearchItem>)
    data class SearchInfo(val totalhits: Int)
    data class SearchItem(val title: String, val snippet: String)
}
