package com.yusufaydin.populartvseries.repository

import com.yusufaydin.populartvseries.model.SeriesDetailModel
import com.yusufaydin.populartvseries.model.SeriesListModel
import com.yusufaydin.populartvseries.service.SeriesAPI
import com.yusufaydin.populartvseries.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class SeriesRepository @Inject constructor(
    private val api : SeriesAPI
) {
    //////////////////////////////////////////////////////
    suspend fun getSeriesList(page:Int):Resource<SeriesListModel>{
        val response=try {
            //not constants try to get it from user somehow
            api.getSeriesList(page)
        }catch (e:Exception){
            return Resource.Error("Error!")
        }
        return Resource.Success(response)
    }


    suspend fun getSeriesDetail(qId:Int):Resource<SeriesDetailModel>{
        val response=try {
            api.getSeriesDetail(qId)
        }catch (e:Exception){
            return Resource.Error("error")
        }
        return Resource.Success(response)
    }

}