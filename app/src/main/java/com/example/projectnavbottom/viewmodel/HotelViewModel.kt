package com.example.projectnavbottom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbtesting.data.entity.Country
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.repository.HotelRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HotelViewModel(private val repository: HotelRepository): ViewModel(){

    val allHotels: StateFlow<List<Hotel>> = repository.allHotels
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    var selectedHotel by mutableStateOf<Hotel?>(null)
        private set

    fun selectHotel(hotel: Hotel){
        selectedHotel = hotel
    }

    fun clearSelectedHotel(hotel: Hotel){
        selectedHotel = null
    }


    fun insertHotel(title: String,
                    description: String,
                    stars: Int,
                    country: Country,
                    imgId: Int){
        viewModelScope.launch {
            val hotel = Hotel(
                title = title,
                description = description,
                stars = stars,
                country = country,
                imgId = imgId
            )
            repository.insert(hotel)
        }
    }

    fun updateHotel(title: String,
                    description: String,
                    stars: Int,
                    country: Country,
                    imgId: Int){
        viewModelScope.launch {
            val hotel = Hotel(
                title = title,
                description = description,
                stars = stars,
                country = country,
                imgId = imgId
            )
            repository.update(hotel)
        }
    }

    fun deleteHotel(hotel: Hotel){
        viewModelScope.launch {
            repository.delete(hotel)
        }
    }


}