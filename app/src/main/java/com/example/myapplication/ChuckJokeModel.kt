package com.example.myapplication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChuckJokeModel(
    val categories: List<String> = emptyList(),
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("icon_url")
    val iconUrl: String = "",
    val id: String = "",
    @SerialName("updated_at")
    val updatedAt: String = "",
    val url: String = "",
    val value: String = ""
)