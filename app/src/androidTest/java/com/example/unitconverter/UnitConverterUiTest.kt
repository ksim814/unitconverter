package com.example.unitconverter

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class UnitConverterUiTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun typingValue_andPressingConvert_showsResult() {
        // Type into the input
        composeRule.onNodeWithText("Input value")
            .performTextInput("10")

        // Tap Convert (default: CelsiusToFahrenheit)
        composeRule.onNodeWithText("Convert")
            .performClick()

        // Check that a result text appears (exact value may vary slightly)
        composeRule.onNodeWithText("Result: 50.00")
            .assertExists()
    }

    @Test
    fun selectingDifferentConversion_updatesResult() {
        // Input a value
        composeRule.onNodeWithText("Input value")
            .performTextInput("1")

        // Open dropdown (button shows current enum name)
        composeRule.onNodeWithText("CelsiusToFahrenheit")
            .performClick()

        // Select another conversion
        composeRule.onNodeWithText("MetersToFeet")
            .performClick()

        // Tap Convert
        composeRule.onNodeWithText("Convert")
            .performClick()

        // Result for 1 meter ≈ 3.28 feet (depends on your rounding)
        composeRule.onNodeWithText("Result: 3.28")
            .assertExists()
    }

    @Test
    fun invalidInput_showsErrorMessage() {
        // Type invalid input
        composeRule.onNodeWithText("Input value")
            .performTextInput("abc")

        // Tap Convert
        composeRule.onNodeWithText("Convert")
            .performClick()

        // Error text from ViewModel
        composeRule.onNodeWithText("Invalid number")
            .assertExists()
    }
}
