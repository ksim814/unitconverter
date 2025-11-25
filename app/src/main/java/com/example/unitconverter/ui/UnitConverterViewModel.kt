package com.example.unitconverter.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.unitconverter.domain.UnitConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class ConversionType {
    CelsiusToFahrenheit,
    FahrenheitToCelsius,
    MetersToFeet,
    FeetToMeters,
    KilogramsToPounds,
    PoundsToKilograms
}

data class ConverterUiState(
    val input: String = "",
    val output: String = "",
    val selectedConversion: ConversionType = ConversionType.CelsiusToFahrenheit,
    val error: String? = null
)

@HiltViewModel
class UnitConverterViewModel @Inject constructor(
    private val converter: UnitConverter
) : ViewModel() {
    var uiState by mutableStateOf(ConverterUiState())
        private set

    fun onInputChange(value: String) {
        uiState = uiState.copy(input = value, error = null)
    }

    fun onConversionSelected(type: ConversionType) {
        uiState = uiState.copy(selectedConversion = type, error = null)
    }

    fun convert() {
        val inputValue = uiState.input.toDoubleOrNull()
        if (inputValue == null) {
            uiState = uiState.copy(error = "Invalid number", output = "")
            return
        }

        val result = when (uiState.selectedConversion) {
            ConversionType.CelsiusToFahrenheit -> converter.celsiusToFahrenheit(inputValue)
            ConversionType.FahrenheitToCelsius -> converter.fahrenheitToCelsius(inputValue)
            ConversionType.MetersToFeet -> converter.metersToFeet(inputValue)
            ConversionType.FeetToMeters -> converter.feetToMeters(inputValue)
            ConversionType.KilogramsToPounds -> converter.kilogramsToPounds(inputValue)
            ConversionType.PoundsToKilograms -> converter.poundsToKilograms(inputValue)
        }

        uiState = uiState.copy(
            output = String.format("%.2f", result),
            error = null
        )
    }
}

