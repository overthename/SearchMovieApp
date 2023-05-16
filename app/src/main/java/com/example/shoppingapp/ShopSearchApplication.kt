package com.example.shoppingapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class ShopSearchApplication : Application(), Configuration.Provider {
    override fun getWorkManagerConfiguration(): Configuration {
        TODO("Not yet implemented")
    }


}