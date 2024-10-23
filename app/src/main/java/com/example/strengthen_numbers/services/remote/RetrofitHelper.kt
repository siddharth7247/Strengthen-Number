package com.example.strengthen_numbers.services.remote

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    private val baseUrl = "https://strengthen-numbers.dev-imaginovation.net/api/v2/"
    private val gson = GsonBuilder()
        .setLenient()
        .create()
    @Provides
    @Singleton
    fun GetRetrofitInstance() : Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(
            gson)).build()
    }
    @Provides
    @Singleton
    fun apiService() : StrengthenNumberServices{
        return GetRetrofitInstance().create(StrengthenNumberServices::class.java)
    }
}