package com.example.shoppingapp.di

import android.content.Context
import androidx.room.Room
import com.example.shoppingapp.data.api.ShopSearchApi
import com.example.shoppingapp.data.db.ShopSearchDatabase
import com.example.shoppingapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //retrofit
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constants.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ShopSearchApi {
        return retrofit.create(ShopSearchApi::class.java)
    }

    //room
    @Singleton
    @Provides
    fun provideShopSearchDatabase(@ApplicationContext context: Context): ShopSearchDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            ShopSearchDatabase::class.java,
            "like-shops"
        ).build()

}