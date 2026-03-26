package com.example.dbtesting.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey



@Entity(tableName = "hotel",
    foreignKeys = [ForeignKey(entity = Country::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("country"),
        onDelete = ForeignKey.CASCADE)])
data class Hotel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val stars: Int,
    val country: Country,
    val imgId: Int
)