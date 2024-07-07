package com.example.interviewnexmedis

data class Product(
    val imageUrl: String,
    val title: String,
    val description: String,
    var isFavorite: Boolean = false // default value false
)

