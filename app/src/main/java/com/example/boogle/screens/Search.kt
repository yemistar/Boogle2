package com.example.boogle.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.boogle.data.Books

@Composable
fun Search(
    searchQuery: (text: String) -> Unit,
    searchResponse: MutableState<List<Books>>
){

    Column( modifier = Modifier.fillMaxSize()) {
        //search bar
        Row(
            modifier = Modifier.padding(10.dp)
        ) {
            SearchBart(searchQuery)
        }

        LazyColumn{
            itemsIndexed(searchResponse.value){ _, item ->
                item.volumeInfo?.title?.let { Text(text = it) }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBart(
    searchQuery: (text: String) -> Unit,
){
    val text = remember {
        mutableStateOf("")
    }
    var active by rememberSaveable { mutableStateOf(false) }

    SearchBar(
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = { 
            Icon(
                imageVector = Icons.Default.Search, 
                contentDescription = "searchIcon"
            )
                      },
        trailingIcon = {
            Icon(
                Icons.Default.MoreVert,
                contentDescription = null
            )
                       },
        placeholder = { Text(text = "Whats the book?")},
        shape =SearchBarDefaults.dockedShape ,
        query = text.value,
        onQueryChange = {
                 text.value = it
        },
        onSearch = {
            active = false
            searchQuery(it)
        },
        active = active ,
        onActiveChange ={
            active = it
        },
    ) {


    }

}