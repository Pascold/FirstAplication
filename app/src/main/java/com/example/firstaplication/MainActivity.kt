package com.example.firstaplication

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstaplication.ui.theme.FirstAplicationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private fun getAndroidName(version: Int): String {
        return when (version) {
            14 -> "UpsideDownCake"
            13 -> "Tiramisu"
            12 -> "Snow Cone"
            11 -> "Red Velvet Cake"
            10 -> "Quince Tart"
            9 -> "Pie"
            8 -> "Oreo"
            7 -> "Nougat"
            6 -> "Marshmallow"
            5 -> "Lollipop"
            else -> "Unknown"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val version = Build.VERSION.SDK_INT
        val release = Build.VERSION.RELEASE
        val codeName = getAndroidName(version)
        setContent {
            FirstAplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    DarkGradientBackground {
                        GreetingCard(version = release, codeName = codeName)
                    }
                }
            }
        }
    }
}

@Composable
fun DarkGradientBackground(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0D0D0D),
                        Color(0xFF121212),
                        Color(0xFF1C1C1C)
                    )
                )
            ),
        content = content
    )
}

@Composable
fun GreetingCard(version: String, codeName: String) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 })
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1E1E1E)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(32.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "XVision Studio",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF64B5F6),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Android Version: $version ($codeName)",
                        fontSize = 18.sp,
                        color = Color(0xFFB0BEC5)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Made by © XVisionStudio",
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingCardPreview() {
    FirstAplicationTheme {
        DarkGradientBackground {
            GreetingCard("14", "UpsideDownCake")
        }
    }
}
