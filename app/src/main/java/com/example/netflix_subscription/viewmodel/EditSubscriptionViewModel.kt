package com.example.netflix_subscription.viewmodel

// EditSubscriptionViewModel.kt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.netflix_subscription.R
import com.example.netflix_subscription.data.App
import com.example.netflix_subscription.data.Category
import com.example.netflix_subscription.data.Subscription

class EditSubscriptionViewModel : ViewModel() {

    private val _subscription = MutableLiveData<Subscription>()
    val subscription: LiveData<Subscription> = _subscription

    private val _apps = MutableLiveData<List<App>>()
    val apps: LiveData<List<App>> = _apps

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val _frequencies = MutableLiveData<List<String>>()
    val frequencies: LiveData<List<String>> = _frequencies

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        _apps.value = listOf(
            App("Netflix", R.drawable.ic_netflix),
            App("Spotify", R.drawable.ic_spotify),
            App("New York Times", R.drawable.ic_nyt),
            App("Wall Street Journal", R.drawable.ic_wsj),
            App("Hulu", R.drawable.ic_hulu),
            App("Apple", R.drawable.ic_apple),
            App("Amazon", R.drawable.ic_amazon)
        )

        _categories.value = listOf(
            Category("Subscription", R.drawable.ic_subscription),
            Category("Utility", R.drawable.ic_utility),
            Category("Card Payment", R.drawable.ic_card),
            Category("Loan", R.drawable.ic_loan),
            Category("Rent", R.drawable.ic_rent)
        )

        _frequencies.value = listOf("Weekly", "Monthly", "Annually")

        // Initialize with Netflix data as shown in screenshot
        _subscription.value = Subscription(
            appName = "Netflix",
            appIcon = R.drawable.ic_netflix,
            amount = 50.00,
            category = "Loan",
            startDate = "Apr 12, 2025",
            frequency = "Weekly",
            remindMe = "2 days before",
            isActive = true
        )
    }

    fun updateApp(app: App) {
        _subscription.value = _subscription.value?.copy(
            appName = app.name,
            appIcon = app.icon
        )
    }

    fun updateAmount(amount: Double) {
        _subscription.value = _subscription.value?.copy(amount = amount)
    }

    fun updateCategory(category: String) {
        _subscription.value = _subscription.value?.copy(category = category)
    }

    fun updateStartDate(date: String) {
        _subscription.value = _subscription.value?.copy(startDate = date)
    }

    fun updateFrequency(frequency: String) {
        _subscription.value = _subscription.value?.copy(frequency = frequency)
    }

    fun toggleActive(isActive: Boolean) {
        _subscription.value = _subscription.value?.copy(isActive = isActive)
    }
}