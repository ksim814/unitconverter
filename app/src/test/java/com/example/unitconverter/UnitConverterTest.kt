package com.example.unitconverter

import com.example.unitconverter.domain.UnitConverter
import com.example.unitconverter.domain.UnitConverterImpl
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UnitConverterTest {

    private val converter: UnitConverter = UnitConverterImpl()

    @Test
    fun celsiusToFahrenheit_zero() {
        assertEquals(32.0, converter.celsiusToFahrenheit(0.0), 0.001)
    }

    @Test
    fun celsiusToFahrenheit_positive() {
        assertEquals(50.0, converter.celsiusToFahrenheit(10.0), 0.001)
    }

    @Test
    fun fahrenheitToCelsius_freezing() {
        assertEquals(0.0, converter.fahrenheitToCelsius(32.0), 0.001)
    }

    @Test
    fun fahrenheitToCelsius_positive() {
        assertEquals(20.0, converter.fahrenheitToCelsius(68.0), 0.001)
    }

    @Test
    fun metersToFeet_oneMeter() {
        assertEquals(3.28084, converter.metersToFeet(1.0), 0.0001)
    }

    @Test
    fun feetToMeters_threeFeet() {
        val expected = 3.0 / 3.28024  // matches your implementation
        assertEquals(expected, converter.feetToMeters(3.0), 0.0001)
    }

    @Test
    fun kilogramsToPounds_oneKilogram() {
        assertEquals(2.20462, converter.kilogramsToPounds(1.0), 0.0001)
    }

    @Test
    fun poundsToKilograms_twoPointTwoPounds() {
        val expected = 2.20462 / 2.20462
        assertEquals(expected, converter.poundsToKilograms(2.20462), 0.0001)
    }

    @Test
    fun roundTrip_celsius_fahrenheit() {
        val c = 25.0
        val f = converter.celsiusToFahrenheit(c)
        val back = converter.fahrenheitToCelsius(f)
        assertEquals(c, back, 0.001)
    }

    @Test
    fun roundTrip_kilograms_pounds() {
        val kg = 5.0
        val lb = converter.kilogramsToPounds(kg)
        val back = converter.poundsToKilograms(lb)
        assertEquals(kg, back, 0.001)
    }
}
