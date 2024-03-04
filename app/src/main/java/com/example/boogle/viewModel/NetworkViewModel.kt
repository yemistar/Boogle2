package com.example.boogle.viewModel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boogle.data.Books
import com.example.boogle.network.BookAPI
import com.example.boogle.utiles.ConvertData
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class NetworkViewModel : ViewModel() {

    /**
     *  @TODO 1. return a mutableState list of the books
     * @TODO 2. make a room data base for the books
     * @TODO 3. make a deeplink for the books
     */
    init {
        getBooks()
    }
   private val _bookList = mutableListOf<Books>().toMutableStateList()
    fun getBooksList(): List<Books>{
        return  _bookList.toList()
    }

   private fun getBooks(){
        viewModelScope.launch {
            val  bookAPI = BookAPI.getInstance()
            val responseBody: ResponseBody? = bookAPI?.getBook()
            val convertData = ConvertData()
            val data = responseBody?.string()

            data?.let {
                _bookList.addAll(convertData.convert(it))
            }


        }
    }
}