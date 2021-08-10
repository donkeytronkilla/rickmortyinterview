package com.donkeytronkilla.myapplication.di

import com.donkeytronkilla.myapplication.api.RickMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(RickMortyApi.BASE_URL).addConverterFactory(
            MoshiConverterFactory.create()
        ).build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): RickMortyApi {
        return retrofit.create(RickMortyApi::class.java)
    }
}