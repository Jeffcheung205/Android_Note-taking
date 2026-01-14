package com.example.myapplication

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class NoteAppInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAddNote() {
        composeTestRule.setContent {
            NoteApp()
        }

        // Type a note
        composeTestRule.onNodeWithText("Enter note").performTextInput("Test Note")
        
        // Click the Add Note button
        composeTestRule.onNodeWithText("Add Note").performClick()
        
        // Verify the note appears in the list
        composeTestRule.onNodeWithText("Test Note").assertExists()
    }
}
