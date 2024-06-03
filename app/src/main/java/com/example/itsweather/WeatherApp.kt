//package com.example.itsweather
//
//data class WeatherApp(
//    val base: String,
//    val clouds: Clouds,
//    val cod: Int,
//    val coord: Coord,
//    val dt: Int,
//    val id: Int,
//    val main: Main,
//    val name: String,
//    val sys: Sys,
//    val timezone: Int,
//    val visibility: Int,
//    val weather: List<Weather>,
//    val wind: Wind
//)

package com.example.itsweather

data class WeatherApp(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Long,
    val name: String,
    val cod: Int
)

data class Coord(
    val lon: Double,
    val lat: Double
)

data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Double,
    val feels_like: Double,
    val temp_min: Double,
    val temp_max: Double,
    val pressure: Int,
    val humidity: Int,
    val sea_level: Int,
    val grnd_level: Int
)

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)

data class Clouds(
    val all: Int
)

data class Sys(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
