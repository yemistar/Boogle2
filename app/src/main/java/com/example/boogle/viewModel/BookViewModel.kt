package com.example.boogle.viewModel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boogle.data.Books
import com.example.boogle.network.BookRepository
import com.example.boogle.utiles.Events
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel(
) {

    private val _events = MutableSharedFlow<Events>()
    val event = _events.asSharedFlow()
   private val _bookList = mutableListOf<Books>().toMutableStateList()

     fun onEvent(events: Events){
        when(events){

            is Events.FirstTime ->{
                sentEvent(Events.Loading)

            }

            is Events.GetBooks -> {

            }

            is Events.SaveBook -> {
               saveBook(events.books)

            }

            is Events.Search -> {
               searchForBook(events.query)

            }

            else -> {}
        }
    }

    private fun saveBook(books: Books){
        viewModelScope.launch{
            bookRepository.saveBook(books)
        }
    }

    private fun searchForBook(query:String){
        var status = false
        viewModelScope.launch {
           val j =launch {
               status= bookRepository.search(query)
            }
            j.join()
            if(status){
                sentEvent(
                    Events.SearchResult(
                        result = bookRepository.getBookList())
                )
            }else {
                // do something
            }
        }
    }

    fun getBooksList(): List<Books>{
        return  _bookList.toList()
    }


    private fun sentEvent(events: Events){
        viewModelScope.launch {
            _events.emit(events)
        }
    }
}

