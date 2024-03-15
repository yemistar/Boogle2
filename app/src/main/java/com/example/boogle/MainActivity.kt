package com.example.boogle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.boogle.data.BookDatabase
import com.example.boogle.network.BookAPI
import com.example.boogle.network.BookRepository
import com.example.boogle.ui.theme.BoogleTheme
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
                   Text(text = "Hello World")
                }
            }
        }
    }
}

