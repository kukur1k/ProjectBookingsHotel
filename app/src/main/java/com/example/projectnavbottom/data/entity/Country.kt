package com.example.dbtesting.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
data class Country(
    @PrimaryKey
    val id: Int,
    val title: String
)