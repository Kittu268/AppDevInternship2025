package com.example.helloworldapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun GreetingScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var greeting by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("welcomeText")
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("nameInput")
        )

        Button(
            onClick = {
                if (name.text.isNotBlank()) {
                    greeting = "Hello, ${name.text}!"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("greetButton")
        ) {
            Text("Greet Me")
        }

        if (greeting.isNotBlank()) {
            Text(
                text = greeting,
                fontSize = 24.sp,
                modifier = Modifier.testTag("greetingText")
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("dashboard") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("dashboardButton")
        ) {
            Text("Go to Dashboard")
        }

        // ✅ New Button to View Test Report
        Button(
            onClick = { navController.navigate("report") },
            modifier = Modifier
                .fillMaxWidth()
                .testTag("viewReportButton")
        ) {
            Text("📊 View Test Report")
        }
    }
}
