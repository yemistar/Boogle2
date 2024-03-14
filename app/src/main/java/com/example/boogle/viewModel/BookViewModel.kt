package com.example.boogle.viewModel

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boogle.data.Books
import com.example.boogle.network.BookRepository
import com.example.boogle.utiles.Events
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class BookViewModel(
    private val bookRepository: BookRepository
) : ViewModel(
) {

    /**
     *  @TODO 1. return a mutableState list of the books
     * @TODO 2. make a room data base for the books
     * @TODO 3. make a deeplink for the books
     */

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
                sentEvent(
                    Events.Status(
                    status = "Book ${events.books.volumeInfo?.title} saved")
                )

            }

            is Events.Search -> {
                searchForBook(events.query)
                sentEvent(
                    Events.SearchResult(result = bookRepository.getBookList())
                )
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
        viewModelScope.launch {
            bookRepository.search(query)
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

