package me.dio.urlshortener.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.dio.urlshortener.data.net.HideUriService
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataRemoteModule {
    @Provides
    fun provideHideUriService(retrofit: Retrofit): HideUriService =
        retrofit.create()

    @Provides
    @Singleton //reutilizado em vários pontos do projeto
    //princípio SOLID - inversão de depen.
    //dependendo de uma abstração, não precisa saber dos detalhes
    fun provideRetrofit(converter: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://hideuri.com")
            //boas práticas - externalizar as dependências dessa dependência
            .addConverterFactory(converter)
            .build()

    @Provides
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()
}