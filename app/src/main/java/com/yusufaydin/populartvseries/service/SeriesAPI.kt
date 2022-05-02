package com.yusufaydin.populartvseries.service

import com.yusufaydin.populartvseries.model.SeriesDetailModel
import com.yusufaydin.populartvseries.model.SeriesListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesAPI {
    //most-popular?page=1

    @GET("most-popular")
    suspend fun getSeriesList(
        @Query("page") page : Int
    ):SeriesListModel


    //show-details?q=arrow
    @GET("show-details")
    suspend fun getSeriesDetail(
        @Query("q") qSeriesId:Int
    ):SeriesDetailModel
}