package com.example.boogle.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
const val APIKEY = "AIzaSyAQ61-SYl2_MpLfj6ffr69t_nUcLeoFSEA"
const val baseUrl = "https://www.googleapis.com/"
const val getBooks = "books/v1/volumes?q=flowers+inauthor:keyes&key=$APIKEY"
interface BookAPI {


    @GET(getBooks)
    suspend fun getBook(): ResponseBody


    companion object{
        private var bookAPI: BookAPI? = null

        fun getInstance(): BookAPI? {

            if (bookAPI == null){
               bookAPI = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(BookAPI::class.java)

            }
            return bookAPI
        }
    }

}