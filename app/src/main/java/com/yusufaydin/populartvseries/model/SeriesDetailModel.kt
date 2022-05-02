package com.yusufaydin.populartvseries.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SeriesDetailModel(
    @SerializedName("tvShow")
    val tvShow: TvShowX
)