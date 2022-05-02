package com.yusufaydin.populartvseries.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class TvShow(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("country")
    val country: String?,
    @SerializedName("image_thumbnail_path")
    val imageThumbnailPath: String?,
    @SerializedName("start_date")
    val startDate: String?,
    @SerializedName("status")
    val status: String?
)