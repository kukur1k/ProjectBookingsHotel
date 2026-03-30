package com.example.projectnavbottom.ui.components


import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.Calendar
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingInputDialog(
     title: String,
     initHotelId: Int = 0,
     initTotalPrice: Double = 0.0,
     initStartDate: String ="",
     initEndDate: String = "",
     initCountGuestAdult: Int = 0,
     initCountGuestChild: Int = 0,
     onDismiss: () -> Unit,
     onConfirm: (Int, Double, String, String, Int, Int) -> Unit
) {
    var hotelId by remember { mutableStateOf(initHotelId) }
    var totalPrice by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf(initStartDate) }
    var endDate by remember { mutableStateOf(initEndDate) }
    var countGuestAdult by remember { mutableStateOf("") }
    var countGuestChild by remember { mutableStateOf("") }


    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },

        text = {

            val context = LocalContext.current
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                OutlinedTextField(
                    value = startDate,
                    onValueChange = { },
                    label = { Text("Дата заезда") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { android.app.DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                startDate = "$dayOfMonth.${month + 1}.$year"
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

                OutlinedTextField(
                    value = endDate,
                    onValueChange = { },
                    label = { Text("Дата заезда") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { android.app.DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                endDate = "$dayOfMonth.${month + 1}.$year"
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


                OutlinedTextField(
                    value = totalPrice,
                    onValueChange = { totalPrice = it},
                    label = { Text("Стоимость") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = countGuestAdult,
                    onValueChange = { newValue ->
                        countGuestAdult = newValue.filter { char -> char.isDigit() }
                    },
                    label = { Text("Количество взрослых") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = countGuestChild,
                    onValueChange = { newValue ->
                        countGuestChild = newValue.filter { char -> char.isDigit() }
                    },
                    label = { Text("Количество детей") },
                    modifier = Modifier.fillMaxWidth()
                )



            }
        },

        confirmButton = {
            TextButton(
                onClick = {

                    val qtyAd = countGuestAdult.toIntOrNull() ?: 0
                    val qtyChild = countGuestChild.toIntOrNull() ?: 0
                    val prc = totalPrice.toDoubleOrNull() ?: 0.0


                    if (true) {

                        onConfirm(hotelId, prc, startDate, endDate, qtyAd, qtyChild)
                        onDismiss()
                    }
                }
            ) {
                Text("Подтвердить")
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Отмена")
            }
        }
    )
}