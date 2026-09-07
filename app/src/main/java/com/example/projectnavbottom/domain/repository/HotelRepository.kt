package com.example.projectnavbottom.domain.repository

import com.example.projectnavbottom.domain.model.Hotel
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    suspend fun getHotels(): Flow<List<Hotel>>
    suspend fun  insertHotels(hotel: Hotel) // принимает доменную модельЮ так как репозиторий работает с данными,
    // и не должен заниматьс поиском (Single)
    suspend fun  updateHotels(hotel: Hotel)
    suspend fun  deleteHotels(hotel: Hotel)

}