package uz.itech.weather.Models

import uz.itech.weather.Models.Main
import uz.itech.weather.Models.Weather

data class MausamData(
    var weather:List<Weather>,
    val main:Main,
    val name:String
)
