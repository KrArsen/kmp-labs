package com.example.timezoneapp.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object AppColors {
    val background = Color(0xFFF5F5F7)      // Apple light gray background
    val surface = Color(0xFFFFFFFF)          // Pure white cards
    val primary = Color(0xFF0071E3)          // Apple blue
    val primaryPressed = Color(0xFF0077ED)   // Apple blue hover
    val text = Color(0xFF1D1D1F)             // Apple dark text
    val textSecondary = Color(0xFF6E6E73)    // Apple gray text
    val divider = Color(0xFFD2D2D7)          // Apple divider
}

val AppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp,
        letterSpacing = (-0.5).sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        letterSpacing = (-0.3).sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        letterSpacing = (-0.2).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = (-0.1).sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        letterSpacing = 0.sp
    )
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            background = AppColors.background,
            surface = AppColors.surface,
            primary = AppColors.primary,
            onPrimary = Color.White,
            onBackground = AppColors.text,
            onSurface = AppColors.text,
        ),
        typography = AppTypography,
        content = content
    )
}
