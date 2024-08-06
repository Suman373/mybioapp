package com.example.myapplication.data

import androidx.compose.runtime.Composable

data class Skill(
    val name: String,
)

@Composable
fun getSkills(): List<Skill> {
    return listOf(
        Skill(name = "Front-end"),
        Skill(name = "Web Dev"),
        Skill(name = "Backend"),
        Skill(name = "Version control"),
        Skill(name = "Docker"),
        Skill(name = "Blogging"),
        Skill(name = "Android Dev"),
    )
}