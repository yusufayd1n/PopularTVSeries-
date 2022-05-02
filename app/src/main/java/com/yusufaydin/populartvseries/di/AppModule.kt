package com.yusufaydin.populartvseries.di

import com.yusufaydin.populartvseries.repository.SeriesRepository
import com.yusufaydin.populartvseries.service.SeriesAPI
import com.yusufaydin.populartvseries.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSeriesRepository(
        api:SeriesAPI
    )=SeriesRepository(api)



    @Singleton
    @Provides
    fun provideSeriesApi():SeriesAPI{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(SeriesAPI::class.java)
    }
}