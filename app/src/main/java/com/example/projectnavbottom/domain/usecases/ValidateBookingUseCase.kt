package com.example.projectnavbottom.domain.usecases

import com.example.projectnavbottom.domain.model.Booking
import com.example.projectnavbottom.domain.model.BookingDialogErrors
import com.example.projectnavbottom.domain.model.ValidationResult

class ValidateBookingUseCase {
    operator fun invoke(booking: Booking, hotelId: Int): ValidationResult {
        val errors = BookingDialogErrors()
        var isValid = true

        if (hotelId <= 0) {
            errors.hotelError = "Отель не выбран"
            isValid = false
        }
        if (booking.startDate.isBlank()) {
            errors.startDateError = "Укажите дату заезда"
            isValid = false
        }
        if (booking.endDate.isBlank()) {
            errors.endDateError = "Укажите дату выезда"
            isValid = false
        }
        if (booking.totalPrice <= 0) {
            errors.totalPriceError = "Некорректная цена"
            isValid = false
        }
        if (booking.countGuestAdult < 1) {
            errors.adultCountError = "Нужен хотя бы один взрослый"
            isValid = false
        }
        if (booking.countGuestChild > 0 && booking.countGuestAdult == 0) {
            errors.childCountError = "Дети не могут быть заселены без взрослых"
            isValid = false
        }
        return ValidationResult(isValid, errors)
    }
}