package com.example.boogle.data

interface BookDatabaseRepo {

    suspend fun getAllBooks(): List<Books>

    suspend fun insertBook(books: Books)

    suspend fun getBookById(id: String)

    suspend fun deleteBook(books: Books)

    companion object{
        private var bookDatabase: BookDatabaseRepo? = null


    }
}