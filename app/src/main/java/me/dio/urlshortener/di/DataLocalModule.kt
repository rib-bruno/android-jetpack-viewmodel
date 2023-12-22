package me.dio.urlshortener.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.dio.urlshortener.data.AppDatabase
import me.dio.urlshortener.data.dao.ShortenedUrlDao
import me.dio.urlshortener.domain.ShortenedUrl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataLocalModule {
    @Provides
    @Singleton //mesma referência para todos
    fun provideDatabase(context: Context) : AppDatabase =
        //TODO - CRIAR O APPDATABASE
        Room.databaseBuilder(context, AppDatabase::class.java, "app.db")
            .build()

    @Provides
    //nao precisa de singleton - pode acontecer algum caso onde só precise de uma vez do dao - sem sentido reaproveitar
    fun provideUrlShortenerDao(db: AppDatabase): ShortenedUrlDao =
        db.shortenedUrlDao()

}