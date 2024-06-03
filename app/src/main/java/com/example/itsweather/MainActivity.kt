package com.example.itsweather

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.itsweather.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = "London"
        fetchData(name)
        searchCity()
    }

    private fun searchCity() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val name = binding.searchView.query.toString()
                fetchData(name)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun fetchData(name: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiInterface::class.java)

        val call = api.getWeatherData(name, "55a3a30f92d50a91d983fea73ccec14f", "metric")

        call.enqueue(object : Callback<WeatherApp> {
            override fun onResponse(call: Call<WeatherApp>, response: Response<WeatherApp>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val temp = responseBody?.main?.temp
                    val windSpeed = responseBody?.wind?.speed
                    val windDeg = responseBody?.wind?.deg
                    val weather = responseBody?.weather?.get(0)?.main
                    val city = responseBody?.name
                    val minTemp = responseBody?.main?.temp_min
                    val maxTemp = responseBody?.main?.temp_max
                    val humidity = responseBody?.main?.humidity
                    val wind = responseBody?.wind?.speed
                    val visibility = responseBody?.visibility
                    val sunrise = responseBody?.sys?.sunrise
                    val sunset = responseBody?.sys?.sunset
                    val pressure = responseBody?.main?.pressure

                    Log.d("MainActivity", "Temperature: $temp, Wind Speed: $windSpeed, Wind Degree: $windDeg")
                    binding.txtTemp.text = "$temp °C"
                    binding.txtWeather.text = "$weather"
                    binding.txtCity.text = "$city"
                    binding.txtMin.text= "Min: $minTemp °C"
                    binding.txtMax.text = "Max: $maxTemp °C"
                    binding.txtHumidity.text = "$humidity%"
                    binding.txtWsp.text = "$wind m/s"
                    binding.txtCondition.text = "$weather m"
                    if (sunrise != null) {
                        binding.txtSunrise.text = "${time(sunrise.toLong())}"
                    }
                    if (sunset != null) {
                        binding.txtSunset.text = "${time(sunset.toLong(),)}"

                    }
                    binding.txtPressure.text = "$pressure hPa "
                    binding.txtDay.text=day(System.currentTimeMillis())
                    binding.txtDate.text=date()

                    conditionChange(weather)


                } else {
                    Log.e("MainActivity", "Request failed with code: ${response.code()}")
                }
            }

            private fun date(): String {
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                return sdf.format(Date())
            }
            private fun day(timestamp: Long): String {
                val sdf = SimpleDateFormat("EEEE ", Locale.getDefault())
                return sdf.format(Date())
            }
            private fun time(timestamp:Long): String {
                val sdf = SimpleDateFormat("hh:mm ", Locale.getDefault())
                return sdf.format(Date(timestamp*1000))
            }


            override fun onFailure(call: Call<WeatherApp>, t: Throwable) {
                Log.e("MainActivity", "Network request failed", t)
            }
        })
    }



    private fun conditionChange(weather: String?) {
        when (weather) {
            "Clear Sky","Sunny","Clear" -> {
                binding.root.setBackgroundResource(R.drawable.sunny_background)
                binding.lottieAnimationView.setAnimation(R.raw.sun)
            }
            "Clouds","Partly Clouds", "Overcast", "Mist", "Foggy" -> {
                binding.root.setBackgroundResource(R.drawable.colud_background)
                binding.lottieAnimationView.setAnimation(R.raw.cloud)
                }
            "Rain", "Drizzle", "Light Rain", "Heavy Rain","Moderate Rain", "Showers" -> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }
            "Snow","Light Snow", "Heavy Snow","Moderate Snow" -> {
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }
            else -> {
                binding.root.setBackgroundResource(R.drawable.sunny_background)
                binding.lottieAnimationView.setAnimation(R.raw.sun)
            }

        }
        binding.lottieAnimationView.playAnimation()
    }


}
