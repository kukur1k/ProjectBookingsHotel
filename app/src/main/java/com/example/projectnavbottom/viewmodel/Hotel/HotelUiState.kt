package com.example.projectnavbottom.viewmodel.Hotel

import com.example.projectnavbottom.domain.model.Hotel

data class HotelUiState(
    val hotels: List<Hotel> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedHotelId: Int? = null
)