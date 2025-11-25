package com.example.unitconverter.domain

import javax.inject.Inject

interface UnitConverter {
    fun celsiusToFahrenheit(c: Double): Double
    fun fahrenheitToCelsius(f: Double): Double
    fun metersToFeet(m: Double): Double
    fun feetToMeters(ft: Double): Double
    fun kilogramsToPounds(kg: Double): Double
    fun poundsToKilograms(lb: Double): Double
}

class UnitConverterImpl @Inject constructor() : UnitConverter {
    override fun celsiusToFahrenheit(c: Double): Double = c * 9 / 5 + 32

    override fun fahrenheitToCelsius(f: Double): Double = (f - 32) * 5 / 9

    override fun metersToFeet(m: Double): Double = m * 3.28084

    override fun feetToMeters(ft: Double): Double = ft / 3.28024

    override fun kilogramsToPounds(kg: Double): Double = kg * 2.20462

    override fun poundsToKilograms(lb: Double): Double = lb / 2.20462
}
