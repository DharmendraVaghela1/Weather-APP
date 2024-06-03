ItsWeather: Android Weather App
ItsWeather is an Android application that provides current weather information for a specified location. The app fetches data from the OpenWeatherMap API and displays the temperature, humidity, wind speed, and other relevant weather details.

Features
Current Weather Data: Retrieves and displays current weather information including temperature, humidity, wind speed, and more.
Location-Based Search: Allows users to search for weather information by city name.
User-Friendly Interface: Clean and intuitive UI with edge-to-edge display support.
Technologies Used
Kotlin: Main programming language for the app.
Retrofit: Networking library to handle API calls and fetch weather data.
Gson: Library for JSON serialization and deserialization.
ViewBinding: Helps to bind UI components in the XML layout to data objects.
Dependencies
com.squareup.retrofit2:retrofit:2.11.0
com.squareup.retrofit2:converter-gson:2.9.0
androidx.core:core-ktx
androidx.appcompat:appcompat
com.google.android.material:material
androidx.activity:activity
androidx.constraintlayout:constraintlayout
junit:junit
androidx.test.ext:junit
androidx.test.espresso:espresso-core
com.airbnb.android:lottie:6.4.1
Getting Started
Prerequisites
Android Studio
OpenWeatherMap API Key


Usage
Launch the app.
Enter the city name for which you want to fetch weather information.
View the current weather details including temperature, humidity, and wind speed.
Code Structure
MainActivity: The main activity that handles UI initialization and API calls.
ApiInterface: Defines the Retrofit API interface for fetching weather data.
Models:
WeatherApp: Data model for the weather response.
Main: Data model for main weather attributes (temperature, pressure, etc.).
Wind: Data model for wind attributes (speed and direction).
Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

License
This project is licensed under the MIT License.

