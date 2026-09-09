package com.example.projectnavbottom.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projectnavbottom.domain.model.Booking
import com.example.projectnavbottom.ui.theme.StyledButton
import com.example.projectnavbottom.viewmodel.Booking.BookingViewModel
import com.example.projectnavbottom.viewmodel.Hotel.HotelViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingFormScreen(
    navController: NavHostController,
    bookingviewModel: BookingViewModel,
    hotelViewModel: HotelViewModel,
    hotelId: Int
) {
    val context = LocalContext.current

    val bookingState by bookingviewModel.uiState.collectAsState()

    val hotelState by hotelViewModel.uiState.collectAsState()

    val hotel = hotelState.hotels.find {it.id == hotelId}

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Бронирование") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.Close, "Назад")
                    }
                }
            )
        }
    ){ paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ){
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            OutlinedTextField(
                value = bookingState.dialogBooking.startDate,
                onValueChange = { },
                label = { Text("Дата заезда") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { android.app.DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            bookingviewModel.updateState{ currentState ->
                                currentState.copy(dialogBooking = currentState.dialogBooking.copy(
                                    startDate = "$dayOfMonth.${month + 1}.$year")
                                )
                            }
                        },
                        year, month, day
                    ).show() }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                }

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = bookingState.dialogBooking.endDate,
                onValueChange = { },
                label = { Text("Дата выезда") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { android.app.DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            bookingviewModel.updateState{ currentState ->
                                currentState.copy(dialogBooking = currentState.dialogBooking.copy(
                                    endDate = "$dayOfMonth.${month + 1}.$year")
                                )
                            }
                        },
                        year, month, day
                    ).show() }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select date"
                        )
                    }
                }

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = bookingState.dialogBooking.totalPrice,
                onValueChange = { bookingviewModel.updateState { currentState ->
                    currentState.copy(
                        dialogBooking = currentState.dialogBooking.copy(
                            totalPrice = it
                        )
                    )
                }},
                label = { Text("Стоимость") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = bookingState.dialogBooking.countGuestAdult,
                onValueChange = { newValue ->
                    bookingviewModel.updateState { currentState ->
                        currentState.copy(
                            dialogBooking = currentState.dialogBooking.copy(
                                countGuestAdult = newValue.filter { char -> char.isDigit() })
                        )
                    }
                },
                label = { Text("Количество взрослых") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = bookingState.dialogBooking.countGuestChild,
                onValueChange = { newValue ->
                    bookingviewModel.updateState { currentState ->
                        currentState.copy(
                            dialogBooking = currentState.dialogBooking.copy(
                                countGuestChild = newValue.filter { char -> char.isDigit() })
                        )
                    }
                },
                label = { Text("Количество детей") },
                modifier = Modifier.fillMaxWidth()
            )

            StyledButton(backColor = Color(0xFF6B8BE2), onClick = {
                val booking = Booking(hotelId = hotel?.id ?: 0,
                    totalPrice = bookingState.dialogBooking.totalPrice.toDouble(),
                    startDate = bookingState.dialogBooking.startDate,
                    endDate = bookingState.dialogBooking.endDate,
                    countGuestAdult = bookingState.dialogBooking.countGuestAdult.toInt(),
                    countGuestChild = bookingState.dialogBooking.countGuestChild.toInt())
                bookingviewModel.insertBooking(booking)
            }){
                Text("Забронировать")
            }

        }

    }
}