package com.example.projectnavbottom.data.repository

import androidx.annotation.WorkerThread
import com.example.dbtesting.data.dao.HotelDao
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.repository.toEntity
import com.example.projectnavbottom.domain.repository.HotelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class HotelRepositoryImpl(private val hotelDao: HotelDao) : HotelRepository {


    override suspend fun getHotels(): Flow<List<com.example.projectnavbottom.domain.model.Hotel>> {
        return hotelDao.getAllHotels()
         .map { entities  ->
                entities.map { entity -> entity.toDomain() }
         }
    }

    override suspend fun insertHotels(hotel: com.example.projectnavbottom.domain.model.Hotel) {
        val entity = hotel.toEntity() // маппингуем и инсертим
        hotelDao.insert(entity)
    }

    override suspend fun updateHotels(hotel: com.example.projectnavbottom.domain.model.Hotel) {
        val entity = hotel.toEntity() // маппингуем и апдейтим
        hotelDao.update(entity)
    }

    override suspend fun deleteHotels(hotel: com.example.projectnavbottom.domain.model.Hotel) {
        val entity = hotel.toEntity() // маппингуем и удаляем
        hotelDao.delete(entity)
    }


}


// маппер
fun com.example.projectnavbottom.domain.model.Hotel.toEntity() = Hotel(
    id = this.id,
    title = this.title,
    description = this.description,
    stars = this.stars,
    countryId = this.countryId,
    imgId = this.imgId
)

fun Hotel.toDomain() = com.example.projectnavbottom.domain.model.Hotel(
    id = this.id,
    title = this.title,
    description = this.description,
    stars = this.stars,
    countryId = this.countryId,
    imgId = this.imgId
)