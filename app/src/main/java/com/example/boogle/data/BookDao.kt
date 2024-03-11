package com.example.boogle.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM book_table")
    suspend fun getAllBooks(): List<Books>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(books: Books)

    @Query("SELECT * FROM book_table WHERE id = :id")
    suspend fun getBookById(id: String):Books

    @Delete
    suspend fun deleteBook(books: Books)
}