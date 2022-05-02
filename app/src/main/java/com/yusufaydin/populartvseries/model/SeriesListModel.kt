package com.yusufaydin.populartvseries.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep


@Keep
data class SeriesListModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: String,
    @SerializedName("tv_shows")
    val tvShows: List<TvShow>
)