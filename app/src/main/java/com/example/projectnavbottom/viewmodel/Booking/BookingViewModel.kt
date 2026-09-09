package com.example.projectnavbottom.viewmodel.Booking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbtesting.data.entity.Booking
import com.example.projectnavbottom.data.repository.BookingRepositoryImpl
import com.example.projectnavbottom.domain.model.BookingDialogData
import com.example.projectnavbottom.domain.repository.BookingRepository
import com.example.projectnavbottom.viewmodel.Hotel.HotelUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookingViewModel(private val repository: BookingRepository): ViewModel(){

    private val _uiState = MutableStateFlow(BookingUiState())

    val uiState: StateFlow<BookingUiState> = _uiState.asStateFlow()

    init {
        loadBookings()
    }

    val state: BookingUiState get() = _uiState.value

//    fun updateState(newState: BookingUiState) {
//        _uiState.value = newState
//    }

    fun updateState(update: (BookingUiState) -> BookingUiState) {
        _uiState.update { update(it) }
    }

    private fun loadBookings() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }

        repository.getBookings().collect { bookings ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    bookings = bookings
                )
            }
        }
    }

    fun showBookingDialog(hotelId: Int){
        _uiState.update { it.copy(
            isDialogVisible = true,
            dialogBooking = BookingDialogData(hotelId = hotelId)
        ) }
    }

    fun hideDialog() {
        _uiState.update { it.copy(isDialogVisible = false) }
    }

    var selectedBooking by mutableStateOf<Booking?>(null)
        private set

    fun selectBooking(bookingId: Int){
        _uiState.update { it.copy(selectedBookingId = bookingId) }
    }

    fun deselectBooking(bookingId: Int){
        _uiState.update { it.copy(selectedBookingId = null) }
    }

    fun getSelectedBooking(): com.example.projectnavbottom.domain.model.Booking? {
        return  uiState.value.bookings.find { it.id == uiState.value.selectedBookingId }
    }

    fun insertBooking(booking: com.example.projectnavbottom.domain.model.Booking)
    {
        viewModelScope.launch {
            repository.insertBooking(booking)
        }

    }


    fun updateBooking(booking: com.example.projectnavbottom.domain.model.Booking)
    {
        viewModelScope.launch {
            repository.updateBooking(booking)
        }

    }

    fun deleteBooking(booking: com.example.projectnavbottom.domain.model.Booking){
        viewModelScope.launch {
            repository.deleteBooking(booking)
        }
    }


}