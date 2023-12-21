package me.dio.urlshortener.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataRemoteModule {
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://hideuri.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}