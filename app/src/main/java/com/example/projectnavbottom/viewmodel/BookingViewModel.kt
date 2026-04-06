package com.example.projectnavbottom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbtesting.data.entity.Booking
import com.example.dbtesting.data.entity.Country
import com.example.dbtesting.data.entity.Hotel
import com.example.projectnavbottom.data.repository.BookingRepository
import com.example.projectnavbottom.data.repository.HotelRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookingViewModel(private val repository: BookingRepository): ViewModel(){

    val allBooking: StateFlow<List<Booking>> = repository.allBookings
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    var selectedBooking by mutableStateOf<Booking?>(null)
        private set

    fun selectBooking(booking: Booking){
        selectedBooking = booking
    }

    fun clearSelectedBooking(booking: Booking){
        selectedBooking = null
    }


    fun insertBooking(hotelId: Int,
                    totalPrice: Double,
                    startdate: String,
                    endDate: String,
                      countGuestAdult: Int,
                      countGuestChild: Int){
        viewModelScope.launch {
            val booking = Booking(
                hotelId = hotelId,
                totalPrice = totalPrice,
                startDate = startdate,
                endDate = endDate,
                countGuestAdult = countGuestAdult,
                countGuestChild = countGuestChild

            )
            repository.insert(booking)
        }
    }

    fun updateBooking(id: Int,
                      hotelId: Int,
                      totalPrice: Double,
                      startdate: String,
                      endDate: String,
                      countGuestAdult: Int,
                      countGuestChild: Int){
        viewModelScope.launch {
            val booking = Booking(
                id = id,
                hotelId = hotelId,
                totalPrice = totalPrice,
                startDate = startdate,
                endDate = endDate,
                countGuestAdult = countGuestAdult,
                countGuestChild = countGuestChild
            )
            repository.update(booking)
        }
    }

    fun deleteBooking(booking: Booking){
        viewModelScope.launch {
            repository.delete(booking)
        }
    }


}