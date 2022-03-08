package uz.itech.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uz.itech.weather.Models.MausamData

interface Api {
    @GET("data/2.5/weather")
    fun getWheather(
        @Query("q") q:String,
        @Query("appid") APIKEY:String,
        @Query("units") units:String
    ):Call<MausamData>
}