package uz.itech.weather.dagger

import dagger.Component
import uz.itech.weather.App
import uz.itech.weather.MainActivity
import uz.itech.weather.SplashActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
   fun inject(app:App)
   fun inject(app:MainActivity)
   fun inject(app:SplashActivity)
}