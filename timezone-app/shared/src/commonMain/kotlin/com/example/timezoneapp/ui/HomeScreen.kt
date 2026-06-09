package com.example.timezoneapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.timezoneapp.DateTimeProviderImpl
import com.example.timezoneapp.theme.AppColors
import com.example.timezoneapp.ui.meeting.MeetingScreen
import com.example.timezoneapp.ui.timezones.TimeZonesScreen
import com.example.timezoneapp.navigation.Screen
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigate: (Screen) -> Unit) {
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
    val tabs = listOf("Timezones", "Meeting", "Catalog")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = "Timezone Planner", 
                        style = MaterialTheme.typography.titleLarge
                    ) 
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
                    2 -> CatalogTab(onNavigate = onNavigate)
                }
            }
        }
    }
}

@Composable
private fun CatalogTab(onNavigate: (Screen) -> Unit) {
    val menuItems = remember {
        listOf(
            "Buttons Demo" to Screen.Buttons,
            "Checkboxes Demo" to Screen.Checkboxes,
            "Chips Demo" to Screen.Chips,
            "DatePicker Demo" to Screen.Datepicker,
            "Dialog Demo" to Screen.Dialog,
            "Divider Demo" to Screen.Divider,
            "Progress Bar Demo" to Screen.ProgressBar,
            "RadioButtons Demo" to Screen.RadioButtons,
            "Switch Demo" to Screen.Switch,
            "TimePicker Demo" to Screen.Timepicker
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.background)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Select a component to view its demo:",
                style = MaterialTheme.typography.titleMedium,
                color = AppColors.textSecondary,
                modifier = Modifier.padding(bottom = 4.dp, start = 4.dp)
            )
        }

        items(menuItems) { (title, screen) ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onNavigate(screen) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.outlinedCardColors(containerColor = AppColors.surface),
                border = CardDefaults.outlinedCardBorder().copy(brush = androidx.compose.ui.graphics.SolidColor(AppColors.divider))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge,
                        color = AppColors.text,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Navigate to $title",
                        tint = AppColors.primary
                    )
                }
            }
        }
    }
}
