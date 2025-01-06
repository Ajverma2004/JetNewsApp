# JetNewsApp 📱

JetNewsApp is a modern Android application that delivers the latest news across various categories like business, entertainment, sports, and more. The app leverages Jetpack Compose for a sleek UI and incorporates real-time updates using the News API.

---

## 📸 Screenshots

<div style="display: flex; justify-content: center; align-items: center; gap: 20px;">

  <!-- Home Screen -->
  <div style="text-align: center;">
    <img src="https://github.com/user-attachments/assets/88eafc81-4430-41b0-85ff-62e1ab2a6cd7" alt="Home Screen" width="200">
    <p><b>Home Screen</b></p>
  </div>

  <!-- Category News -->
  <div style="text-align: center;">
    <img src="https://github.com/user-attachments/assets/c8d8689c-ac16-472e-981c-c77242b50e1c" alt="Category News" width="200">
    <p><b>Category News</b></p>
  </div>

  <!-- Article Details -->
  <div style="text-align: center;">
    <img src="https://github.com/user-attachments/assets/bab8286a-78b2-4e6a-9f04-637ba1ba5e4b" alt="Article Details" width="200">
    <p><b>Article Details</b></p>
  </div>

</div>


---

## 🚀 Features

- Browse news articles by category (e.g., business, entertainment, sports).
- View detailed articles with integrated `WebView`.
- Intuitive and responsive UI built with Jetpack Compose.
- Search functionality for finding specific news articles.
- Offline caching for enhanced performance.
- Dynamic animations for navigation.

---

## 🛠️ Technologies Used

- **Jetpack Compose**: For building the UI.
- **Navigation Component**: For managing in-app navigation.
- **Accompanist Navigation Animation**: For smooth transitions.
- **News API**: For fetching real-time news updates.
- **Retrofit**: For API calls.
- **Coil**: For image loading.
- **Hilt**: For dependency injection.

---

---

## 🖥️ Setup Instructions
- **Clone the Repository**:
   ```bash
   git clone https://github.com/Ajverma2004/JetNewsApp.git
   cd JetNewsApp
-  Open in Android Studio: Open the project in Android Studio.

-  Add Your API Key: Add your News API key in the Constants.kt file:
    ```bash
    object Constants {
        const val API_KEY = "your-api-key-here"
        const val BASE_URL = "https://newsapi.org/"
    }
    
-  Sync Gradle: Sync the Gradle project to download all dependencies.

-  Run the App: Build and run the app on an emulator or a physical device.

## ✨ Upcoming Features
- User authentication for personalized news feeds.
- Save articles to a favorites list.
- Push notifications for breaking news.
