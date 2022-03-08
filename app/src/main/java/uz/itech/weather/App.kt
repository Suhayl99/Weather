package uz.itech.weather

import android.app.Application
import uz.itech.weather.dagger.AppComponent
import uz.itech.weather.dagger.DaggerAppComponent


class App:Application() {
    companion object{
        lateinit var app: App
    }
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        app=this
        appComponent= DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }
}