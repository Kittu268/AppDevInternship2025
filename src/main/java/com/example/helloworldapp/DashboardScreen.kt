package com.example.helloworldapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(navController: NavController) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var greeting by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Welcome to Dashboard!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("welcomeText")
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") },
            singleLine = true,
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

        DashboardStatsSection()

        Spacer(modifier = Modifier.height(24.dp))

        RecentActivitySection()

        Spacer(modifier = Modifier.height(24.dp))

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

@Composable
fun DashboardStatsSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatCard(title = "Tasks", value = "12", tag = "tasksCard")
        StatCard(title = "Completed", value = "8", tag = "completedCard")
    }
}

@Composable
fun StatCard(title: String, value: String, tag: String) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .height(100.dp)
            .testTag(tag),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = value, style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
fun RecentActivitySection() {
    Text(
        text = "Recent Activity",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    val activities = listOf(
        "ATM Simulator Completed",
        "Library System Milestone",
        "HelloWorld Compose Pushed",
        "Navigation Added",
        "UI Test Passed"
    )

    LazyColumn {
        items(activities) { activity ->
            ActivityItem(title = activity, date = "2025-08-18")
        }
    }
}

@Composable
fun ActivityItem(title: String, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title, style = MaterialTheme.typography.bodyLarge)
            Text(text = date, style = MaterialTheme.typography.bodySmall)
        }
    }
}
