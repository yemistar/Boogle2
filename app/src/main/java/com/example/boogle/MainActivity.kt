package com.example.boogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.boogle.data.BookDatabase
import com.example.boogle.data.Books
import com.example.boogle.network.BookAPI
import com.example.boogle.network.BookRepository
import com.example.boogle.screens.Search
import com.example.boogle.ui.theme.BoogleTheme
import com.example.boogle.utiles.Events
import com.example.boogle.viewModel.BookViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val bookDatabase = BookDatabase.getDatabase(this)
            val bookApi = BookAPI.getInstance()
            val bookRepository = BookRepository(
                bookApi,
                bookDatabase?.bookDao())
            val vm = BookViewModel(bookRepository)
            vm.getBooksList()
            BoogleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val searchResponse = remember {
                        mutableStateOf(emptyList<Books>())
                    }
                    LaunchedEffect(key1 =this ){
                        vm.event.collect{
                            when(it){
                                is Events.SearchResult ->{
                                    searchResponse.value = it.result
                                }
                                else -> {}
                            }
                        }
                    }
                    Column {
                        Search(
                            searchQuery = {
                                vm.onEvent(Events.Search(query = it))
                            },
                            searchResponse
                        )
                    }
                }
            }
        }
    }
}

