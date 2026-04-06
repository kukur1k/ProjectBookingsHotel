package com.example.projectnavbottom.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dbtesting.data.entity.Country
import com.example.projectnavbottom.data.repository.CountryRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CountryViewModel(private val repository: CountryRepository) : ViewModel() {


    val allCountries: StateFlow<List<Country>> = repository.allCountries
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    var selectedCountry by mutableStateOf<Country?>(null)
        private set

    fun selectCountry(country: Country) {
        selectedCountry = country
    }

    fun clearSelectedCountry() {
        selectedCountry = null
    }

    fun insertCountry(id: Int, title: String) {
        viewModelScope.launch {
            val country = Country(
                id = id,
                title = title
            )
            repository.insert(country)
        }
    }

    fun updateCountry(id: Int, title: String) {
        viewModelScope.launch {
            val country = Country(
                id = id,
                title = title
            )
            repository.update(country)
        }
    }

    fun deleteCountry(country: Country) {
        viewModelScope.launch {
            repository.delete(country)
        }
    }

    suspend fun getCountryById(id: Int): Country? {
        return repository.getCountryById(id)
    }


}