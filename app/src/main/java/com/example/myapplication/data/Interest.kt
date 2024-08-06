package com.example.myapplication.data
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.myapplication.R

data class Interest(
    val name: String,
    val icon: Painter
)

@Composable
fun getInterests(): List<Interest> {
    return listOf(
            Interest(name = "Coding", icon = painterResource(id = R.drawable.baseline_laptop_black_20)),
            Interest(name = "Cycling", icon = painterResource(id = R.drawable.bicycle)),
            Interest(name = "Sports", icon = painterResource(id = R.drawable.sports)),
            Interest(name = "Gaming", icon = painterResource(id = R.drawable.gaming)),
            Interest(name = "Self-improvement", icon = painterResource(id = R.drawable.self_improvement))
        )
}