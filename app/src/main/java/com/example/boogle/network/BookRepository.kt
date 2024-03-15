package com.example.boogle.network

import androidx.compose.runtime.toMutableStateList
import com.example.boogle.data.BookDao
import com.example.boogle.data.Books
import com.example.boogle.utiles.ConvertData
import okhttp3.ResponseBody
import retrofit2.Response

class BookRepository(
    private val bookAPI: BookAPI?,
    private val bookDatabase: BookDao?
) {

    private val _bookList = mutableListOf<Books>().toMutableStateList()
    suspend fun getBooks(){
        val responseBody: Response<ResponseBody>? = bookAPI?.getBook()
        val convertData = ConvertData()
        val data = responseBody?.body()?.string()
        if(responseBody?.isSuccessful == true){
            data.let {
                _bookList.addAll(convertData.convert(it.orEmpty()))
            }
            _bookList.forEach { books ->
                bookDatabase?.insertBook(books)
            }

        }
    }

    fun getBookList(): List<Books>{
        return _bookList.toList()
    }

   suspend fun saveBook(books: Books) {
       bookDatabase?.insertBook(books)
    }

   suspend fun search(query: String) {
       val responseBody: Response<ResponseBody>? =bookAPI?.queryBook(query, APIKEY)
       val convertData = ConvertData()
       val data = responseBody?.body()?.string()
       if(responseBody?.isSuccessful == true){
           _bookList.clear()
           data.let {
               _bookList.addAll(convertData.convert(it.orEmpty()))
           }
       }
    }

}