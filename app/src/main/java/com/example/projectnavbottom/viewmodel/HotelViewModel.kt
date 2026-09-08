package com.example.projectnavbottom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbtesting.data.entity.Country
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.repository.HotelRepositoryImpl
import com.example.projectnavbottom.domain.repository.HotelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HotelViewModel(private val repository: HotelRepository): ViewModel(){

    private val _uiState = MutableStateFlow(HotelUiState())

    val uiState: StateFlow<HotelUiState> = _uiState.asStateFlow()

    init {
        loadHotels()
    }

    // загрузка отелей из flow в лист и запись в состояние
    private fun loadHotels() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }

        repository.getHotels().collect { hotels ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    hotels = hotels
                )
            }
        }
    }



    fun selectHotel(hotelId: Int){
         _uiState.update { it.copy(selectedHotelId = hotelId) }
    }

    fun deselectHotel(hotelId: Int){
        _uiState.update { it.copy(selectedHotelId = null) }
    }

    fun getSelectedHotel(): com.example.projectnavbottom.domain.model.Hotel? {
        return  uiState.value.hotels.find { it.id == uiState.value.selectedHotelId }
    }


    fun insertHotel(hotel: com.example.projectnavbottom.domain.model.Hotel)
    {
        viewModelScope.launch {
            repository.insertHotels(hotel)
        }

    }
//    fun insertHotel(title: String,
//                    description: String,
//                    stars: Int,
//                    countryId: Int,
//                    imgId: Int){
//        viewModelScope.launch {
//            val hotel = Hotel(
//                title = title,
//                description = description,
//                stars = stars,
//                countryId = countryId,
//                imgId = imgId
//            )
//            repository.insert(hotel)
//        }
//    }
//
//    fun updateHotel(title: String,
//                    description: String,
//                    stars: Int,
//                    countryId: Int,
//                    imgId: Int){
//        viewModelScope.launch {
//            val hotel = Hotel(
//                title = title,
//                description = description,
//                stars = stars,
//                countryId = countryId,
//                imgId = imgId
//            )
//            repository.update(hotel)
//        }
//    }
//
//    fun deleteHotel(hotel: Hotel){
//        viewModelScope.launch {
//            repository.delete(hotel)
//        }
//    }




}