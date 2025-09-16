package com.example.netflix_subscription.data

// SubscriptionModel.kt
data class Subscription(
    val id: String = "",
    val appName: String = "",
    val appIcon: Int = 0,
    val amount: Double = 0.0,
    val category: String = "",
    val startDate: String = "",
    val frequency: String = "",
    val remindMe: String = "",
    val isActive: Boolean = true
)

data class App(
    val name: String,
    val icon: Int
)

data class Category(
    val name: String,
    val icon: Int
)