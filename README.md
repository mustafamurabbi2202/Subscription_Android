# Android Subscription Management App
 
## Overview
 
This Android app allows users to manage subscriptions efficiently using a clean MVVM (Model-View-ViewModel) architecture. The user can select and manage subscriptions like Netflix, Spotify, etc., enter subscription details such as amount, category, start date, and frequency, and view or edit them at any time.
 
## Features
 
1. **Bottom Sheets for User Interaction**:
   - **App Selection**: When the user clicks the app name field, a bottom sheet opens where they can search or select apps like Netflix, Spotify, New York Times, etc.
   - **Amount Selection**: When the user clicks the amount field, a bottom sheet appears where they can input the subscription amount, which will be reflected in the subscription details.
   - **Category Selection**: Upon clicking the category field, a bottom sheet opens where the user can select the type of subscription, such as:
     - Subscription
     - Utility
     - Card Payment
     - Loan
     - Rent
   - **Start Date Selection**: When the user clicks on the start date field, a date picker bottom sheet will appear, allowing them to choose the subscription start date.
   - **Frequency Selection**: A bottom sheet opens when the user clicks on the frequency field, where they can select the frequency of payment (Weekly, Monthly, Annually).
 
2. **MVVM Architecture**:
   - **UI Layer**: Responsible for user interface interactions. Handles displaying data from the ViewModel and forwarding user actions to the ViewModel.
   - **ViewModel Layer**: Holds the app’s UI-related data and manages logic for data manipulation. Observes data changes and ensures the UI reflects the current state.
   - **Data Layer**: Responsible for handling data-related operations. This layer deals with data fetching, saving, and processing.
 
## Structure
 
### Modules
 
- **UI Module**: Contains the activities, fragments, and UI components (e.g., bottom sheets, buttons, text fields).
- **ViewModel Module**: Contains the ViewModel classes, which hold the business logic and interact with the data layer.
- **Data Module**: Deals with data handling, such as retrieving data from local storage or remote sources (e.g., databases, APIs).
 
### Folder Structure
 
```plaintext
- ui/
    - EditSubscriptionActivity.kt
    - AppSelectionAdapter
- viewmodel/
    - EditSubscriptionViewModel.kt
- data/
    - SubscriptionModel.kt