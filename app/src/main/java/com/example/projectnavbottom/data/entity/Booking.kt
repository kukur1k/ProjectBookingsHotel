package com.example.dbtesting.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "booking",
    foreignKeys = [ForeignKey(entity = Hotel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("hotel"),
        onDelete = ForeignKey.CASCADE)])
data class Booking(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val hotel: Hotel,
    val totalPrice: Double,
    val startDate: String,
    val endDate: String
)
