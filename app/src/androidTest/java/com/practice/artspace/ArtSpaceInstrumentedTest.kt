package com.practice.artspace

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.practice.artspace.ui.theme.ArtSpaceTheme
import org.junit.Rule
import org.junit.Test

class ArtSpaceInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testArtApp() {
        composeTestRule.setContent{
            ArtSpaceTheme {
                ArtApp()
            }
        }
        composeTestRule.onNodeWithText("Previous").assertExists()
        composeTestRule.onNodeWithText("Next").assertExists()

        //initial state check based on index = 1
       composeTestRule.onNodeWithText("African Renaissance Monument").assertIsDisplayed()

        //click on next button
        composeTestRule.onNodeWithText("Next").performClick()
        composeTestRule.onNodeWithText("Eiffel Tower").assertIsDisplayed()

        //click on previous button
        composeTestRule.onNodeWithText("Previous").performClick()
        composeTestRule.onNodeWithText("African Renaissance Monument").assertIsDisplayed()

    }
}


