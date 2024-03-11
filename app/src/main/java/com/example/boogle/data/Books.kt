package com.example.boogle.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import kotlinx.serialization.Serializable

@Entity(tableName = "book_table")
@Serializable
data class Books (
    @PrimaryKey val id:String,
    val selfLink: String?,
    @TypeConverters(SaleInfoConverter::class)
    val saleInfo: SaleInfo?,

    @TypeConverters(VolumeInfoConverter::class)
    val volumeInfo: VolumeInfo?,
    @TypeConverters(PDFConverter::class)
    val pdf: PDF?,
    val webReaderLink: String?,

    @TypeConverters(SearchInfoConverter::class)
    val searchInfo: SearchInfo?
)

@Serializable
data class SaleInfo(
    val country: String,
    val saleability: String,
    val isEbook: Boolean

)

class SaleInfoConverter{
    private val gson = Gson()
    @TypeConverter
    fun formSource(value: String) : SaleInfo{
       return gson.fromJson(value,SaleInfo::class.java)
    }

    @TypeConverter
    fun saleInfoToString(value: SaleInfo?): String {
       return gson.toJson(value)
    }
}

@Serializable
data class VolumeInfo(
    val title: String,
    @TypeConverters(ListConverters::class)
    val authors: List<String>,
    val publishedDate: String,
    val description: String,
    val pageCount: String,
    val printType: String,
    val averageRating: Double,
    val ratingsCount: Int,
    @TypeConverters(ImageLinksConverter::class)
    val imageLinks: ImageLinks,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    @TypeConverters(ListConverters::class)
    val categories: List<String>,
    val canonicalVolumeLink: String
)

class VolumeInfoConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromSource(value: String) : VolumeInfo{
        return gson.fromJson(value,VolumeInfo::class.java)
    }

    @TypeConverter
    fun pdfToString(value: VolumeInfo?) : String {
        return gson.toJson(value)
    }
}

class ListConverters{
    private val gson = Gson()

    @TypeConverter
    fun listToJson(value: List<String>?): String = gson.toJson(value)
    @TypeConverter
    fun jsonStringToList(value: String?) =
        gson.fromJson(value,Array<String>::class.java).toList()
}
@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)


class ImageLinksConverter {
    private val gson = Gson()
    @TypeConverter
    fun fromSource(value: String) : ImageLinks{
        return gson.fromJson(value,ImageLinks::class.java)
    }

    @TypeConverter
    fun pdfToString(value: ImageLinks?) : String {
        return gson.toJson(value)
    }
}

@Serializable
data class PDF(
    val isAvailable: Boolean,
    val acsTokenLink: String
)

class PDFConverter{
    private val gson = Gson()
    @TypeConverter
    fun fromSource(value: String) : PDF{
        return gson.fromJson(value,PDF::class.java)
    }

    @TypeConverter
    fun pdfToString(value: PDF?) : String {
        return gson.toJson(value)
    }
}

@Serializable
data class SearchInfo(
    val textSnippet: String
)

class SearchInfoConverter{
    private val gson = Gson()
    @TypeConverter
    fun fromSource(value: String) : SearchInfo{
        return gson.fromJson(value,SearchInfo::class.java)
    }

    @TypeConverter
    fun pdfToString(value: SearchInfo?) : String {
        return gson.toJson(value)
    }
}