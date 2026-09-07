package com.example.projectnavbottom.domain.model

data class Hotel(
    val id: Int = 0,
    val title: String,
    val description: String,
    val stars: Int,
    val countryId: Int,
    val imgId: Int
)