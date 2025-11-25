package com.example.unitconverter

import com.example.unitconverter.domain.UnitConverter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HiltIntegrationTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var converter: UnitConverter

    @Test
    fun hiltInjectsConverter() {
        hiltRule.inject()
        assertNotNull(converter)
    }
}