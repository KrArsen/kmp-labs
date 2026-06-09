package com.example.timezoneapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.example.timezoneapp.DateTimeProviderImpl
import com.example.timezoneapp.theme.AppColors
import com.example.timezoneapp.ui.meeting.MeetingScreen
import com.example.timezoneapp.ui.timezones.TimeZonesScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    val dateTimeProvider = remember { DateTimeProviderImpl() }
    
    // Shared state of selected time zones
    val selectedTimeZones = remember { 
        mutableStateListOf<String>().apply {
            // Default to current time zone
            val defaultZone = dateTimeProvider.getCurrentTimeZone()
            add(defaultZone)
            // Add UTC by default to make it look active and useful
            if (defaultZone != "UTC") {
                add("UTC")
            }
        }
    }
    
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Timezones", "Meeting")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Timezone Planner", 
                        style = MaterialTheme.typography.titleLarge
                    ) 
                },
                actions = {
                    IconButton(onClick = { onNavigate("main") }) {
                        Icon(
                            imageVector = Icons.Default.List, 
                            contentDescription = "Component Catalog",
                            tint = AppColors.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.surface,
                    titleContentColor = AppColors.text
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(AppColors.background)
        ) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = AppColors.surface,
                contentColor = AppColors.primary,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = AppColors.primary
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { 
                            Text(
                                text = title,
                                style = MaterialTheme.typography.bodyLarge
                            ) 
                        },
                        selectedContentColor = AppColors.primary,
                        unselectedContentColor = AppColors.textSecondary
                    )
                }
            }

            Box(modifier = Modifier.fillMaxSize()) {
                when (selectedTab) {
                    0 -> TimeZonesScreen(
                        dateTimeProvider = dateTimeProvider,
                        selectedTimeZones = selectedTimeZones
                    )
                    1 -> MeetingScreen(
                        dateTimeProvider = dateTimeProvider,
                        selectedTimeZones = selectedTimeZones
                    )
                }
            }
        }
    }
}
