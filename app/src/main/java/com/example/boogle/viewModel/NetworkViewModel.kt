package com.example.boogle.viewModel

import android.content.Context
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boogle.data.BookDatabase
import com.example.boogle.data.Books
import com.example.boogle.network.BookAPI
import com.example.boogle.utiles.ConvertData
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class NetworkViewModel(applicationContext: Context) : ViewModel(
) {

    /**
     *  @TODO 1. return a mutableState list of the books
     * @TODO 2. make a room data base for the books
     * @TODO 3. make a deeplink for the books
     */
    init {
        getBooks(applicationContext)
    }
   private val _bookList = mutableListOf<Books>().toMutableStateList()


    fun getBooksList(): List<Books>{
        return  _bookList.toList()
    }

   private fun getBooks(applicationContext: Context) {
        viewModelScope.launch {
            val  bookAPI = BookAPI.getInstance()
            val responseBody: ResponseBody? = bookAPI?.getBook()
            val convertData = ConvertData()
            val data = responseBody?.string()
            val bookDb = BookDatabase.getDatabase(applicationContext)

            data?.let {
                _bookList.addAll(convertData.convert(it))

                bookDb?.bookDao()?.insertBook(_bookList[0])
            }


        }
    }
}