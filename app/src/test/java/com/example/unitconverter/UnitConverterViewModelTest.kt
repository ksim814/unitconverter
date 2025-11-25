package com.example.unitconverter

import com.example.unitconverter.domain.UnitConverter
import com.example.unitconverter.ui.ConversionType
import com.example.unitconverter.ui.UnitConverterViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Before
import org.junit.Test

class UnitConverterViewModelTest {

    private lateinit var converter: UnitConverter
    private lateinit var viewModel: UnitConverterViewModel

    @Before
    fun setup() {
        converter = mockk(relaxed = true)
        viewModel = UnitConverterViewModel(converter)
    }

    // SUCCESS CASES

    @Test
    fun convertCelsiusToFahrenheit_success() {
        viewModel.onInputChange("10.0")
        viewModel.onConversionSelected(ConversionType.CelsiusToFahrenheit)
        every { converter.celsiusToFahrenheit(10.0) } returns 50.0

        viewModel.convert()

        assertEquals("50.00", viewModel.uiState.output)
        assertNull(viewModel.uiState.error)
    }

    @Test
    fun convertFeetToMeters_success() {
        viewModel.onInputChange("3.0")
        viewModel.onConversionSelected(ConversionType.FeetToMeters)
        every { converter.feetToMeters(3.0) } returns 0.9144

        viewModel.convert()

        assertEquals("0.91", viewModel.uiState.output)
        assertNull(viewModel.uiState.error)
    }

    // ERROR CASES

    @Test
    fun convertWithInvalidNumber_showsError() {
        viewModel.onInputChange("abc")

        viewModel.convert()

        assertEquals("Invalid number", viewModel.uiState.error)
        assertEquals("", viewModel.uiState.output)
    }

    @Test
    fun errorClearsAfterValidInput() {
        viewModel.onInputChange("bad")
        viewModel.convert()
        assertEquals("Invalid number", viewModel.uiState.error)

        viewModel.onInputChange("5.0")
        every { converter.celsiusToFahrenheit(5.0) } returns 41.0

        viewModel.convert()

        assertNull(viewModel.uiState.error)
        assertEquals("41.00", viewModel.uiState.output)
    }

    // STATE UPDATES

    @Test
    fun selectingDifferentConversionUpdatesState() {
        viewModel.onConversionSelected(ConversionType.KilogramsToPounds)

        assertEquals(
            ConversionType.KilogramsToPounds,
            viewModel.uiState.selectedConversion
        )
        assertNull(viewModel.uiState.error)
    }
}
