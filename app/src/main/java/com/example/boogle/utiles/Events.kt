package com.example.boogle.utiles

import com.example.boogle.data.Books

sealed class Events {
    object FirstTime: Events()
    object Loading: Events()
    data class Error(val message:String) : Events()
    data class Search(val query:String): Events()

    data class Status(val status: String): Events()


    data class SearchResult(val result: List<Books>): Events()

    data class SaveBook(val books: Books): Events()

    data class GetBooks(val books: List<Books>): Events()

}