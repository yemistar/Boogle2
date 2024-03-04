package com.example.boogle.data

import kotlinx.serialization.Serializable

@Serializable
data class Books (
    val id:String,
    val selfLink: String,
    val saleInfo: SaleInfo,
    val volumeInfo: VolumeInfo,
    val pdf: PDF,
    val webReaderLink: String,
    val searchInfo: SearchInfo
)

@Serializable
data class SaleInfo(
    val country: String,
    val saleability: String,
    val isEbook: Boolean

)

@Serializable
data class VolumeInfo(
    val title: String,
    val authors: List<String>,
    val publishedDate: String,
    val description: String,
    val pageCount: String,
    val printType: String,
    val averageRating: Double,
    val ratingsCount: Int,
    val imageLinks: ImageLinks,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val categories: List<String>,
    val canonicalVolumeLink: String
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)

@Serializable
data class PDF(
    val isAvailable: Boolean,
    val acsTokenLink: String
)

@Serializable
data class SearchInfo(
    val textSnippet: String
)