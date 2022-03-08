package uz.itech.weather

import android.media.session.MediaSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.TokenWatcher
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.core.content.getSystemService
import com.airbnb.lottie.parser.moshi.JsonReader
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.itech.weather.Models.Main
import uz.itech.weather.Models.MausamData
import uz.itech.weather.Models.Weather
import uz.itech.weather.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.zip.DataFormatException
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var api: Api
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        App.app.appComponent.inject(this)
        fetchWeather("Fergana")

        fun getCurrentDate(): String {
            val sdf = SimpleDateFormat("dd MMMM yyyy")
            return sdf.format(Date())
        }
        binding.tvdate.text = getCurrentDate()

        binding.fabSearching.setOnClickListener {
            hideKeyBoard()
            if (TextUtils.isEmpty(binding.editCity.text.toString())) {
                binding.editCity.setText("Please enter city")
                return@setOnClickListener
            }
            var CITY_NAME: String = binding.editCity.text.toString()
            fetchWeather(CITY_NAME)
        }


    }

    private fun hideKeyBoard() {
        val inputMetodManager:InputMethodManager= getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMetodManager.hideSoftInputFromWindow(binding.constraintLayout.applicationWindowToken, 0)
    }

    private fun fetchWeather(city: String) {

        api.getWheather(city, "388c78cc4a667079c8f6814d3cd7b899", "metric").enqueue(object :
            Callback<MausamData> {
            override fun onResponse(call: Call<MausamData>, response: Response<MausamData>) {
                if (response.isSuccessful) {
                    val mausamData = response.body()!!

                    binding.tvtemp.text = mausamData.main.temp.toString()
                    binding.tvmaxTemp.text = mausamData.main.temp_max.toString()
                    binding.tvminTemp.text = mausamData.main.temp_min.toString()
                    binding.tvpressure.text = mausamData.main.pressure.toString()
                    binding.tvhumidity.text = mausamData.main.humidity.toString()
                    binding.tvCity.text = mausamData.name.toString()

                    val description = mausamData.weather

                    for( i in description.indices){
                        binding.description.text=description[i].description
                    }
                }
            }

            override fun onFailure(call: Call<MausamData>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })

    }
}