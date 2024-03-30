package com.example.boogle.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val APIKEY = ""
const val baseUrl = "https://www.googleapis.com/"
const val getBooks = "books/v1/volumes?q=flowers+inauthor:keyes&key=$APIKEY"
interface BookAPI {


    @GET(getBooks)
    suspend fun getBook(): Response<ResponseBody>

    @GET("books/v1/volumes")
    suspend fun queryBook(@Query("q") query: String, @Query("keyes&key") key: String): Response<ResponseBody>


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