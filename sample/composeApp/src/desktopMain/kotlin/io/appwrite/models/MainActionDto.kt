package io.appwrite.models

import io.appwrite.navigation.Screen

data class MainActionDto(
    val index: Int,
    val title: String,
    val screen: Screen,
    val isPrimary: Boolean = false,
)
