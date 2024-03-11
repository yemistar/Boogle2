package com.example.boogle.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Books::class], version = 2, exportSchema = false)
@TypeConverters(SaleInfoConverter::class,VolumeInfoConverter::class,ListConverters::class,
    ImageLinksConverter::class, PDFConverter::class,SearchInfoConverter::class)
abstract class BookDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var BOOK_DB_INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context): BookDatabase? {
            if(BOOK_DB_INSTANCE == null){
                synchronized(this){
                    BOOK_DB_INSTANCE = Room.databaseBuilder(
                        context = context.applicationContext,
                        klass = BookDatabase::class.java,
                        name = "book_table"
                    ).fallbackToDestructiveMigration()
                        .build()
                }

            }
            return BOOK_DB_INSTANCE
        }
    }
}