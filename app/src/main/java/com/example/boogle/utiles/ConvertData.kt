package com.example.boogle.utiles


import com.example.boogle.data.Books
import com.google.gson.Gson
import org.json.JSONObject

class ConvertData {

    fun convert(data: String): List<Books> {
        val jsonObject = JSONObject(data)
        val list = mutableListOf<Books>()
        val gson = Gson()
        val size:Int = jsonObject.getJSONArray("items").length()

        for (value: Int in 0 until size){
            val item = jsonObject.getJSONArray("items").get(value)
            val book = gson.fromJson(item.toString(),Books::class.java)
            list.add(book)
        }
       return list
    }

}