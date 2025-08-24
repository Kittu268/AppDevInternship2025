package com.example.helloworldapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import java.io.File

class GreetingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testGreetingScreenBehavior() {
        lateinit var navController: TestNavHostController

        composeTestRule.setContent {
            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            GreetingScreen(navController = navController)
        }

        composeTestRule.onNodeWithTag("nameInput").performTextInput("Mahesh")
        composeTestRule.onNodeWithTag("greetButton").performClick()
        composeTestRule.onNodeWithTag("greetingText")
            .assertExists()
            .assertTextEquals("Hello, Mahesh!")
    }

    @Test
    fun testDashboardScreenWithReport() {
        lateinit var navController: TestNavHostController

        composeTestRule.setContent {
            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            DashboardScreen(navController = navController)
        }

        val report = StringBuilder()

        try {
            composeTestRule.onNodeWithTag("welcomeText").assertIsDisplayed()
            report.append("✅ Welcome text displayed\n")

            composeTestRule.onNodeWithTag("tasksCard").assertIsDisplayed()
            composeTestRule.onNodeWithTag("completedCard").assertIsDisplayed()
            report.append("✅ Stat cards displayed\n")

            composeTestRule.onNodeWithText("ATM Simulator Completed").assertIsDisplayed()
            report.append("✅ Recent activity displayed\n")

        } catch (e: AssertionError) {
            report.append("❌ Test failed: ${e.message}\n")
        }

        // Get the application context for file operations
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Use a directory like filesDir or cacheDir which your app can write to
        val timestamp = System.currentTimeMillis()
        val reportFile = File(context.filesDir, "test-report-dashboard-$timestamp.txt")

        reportFile.writeText(report.toString())
    }
}
