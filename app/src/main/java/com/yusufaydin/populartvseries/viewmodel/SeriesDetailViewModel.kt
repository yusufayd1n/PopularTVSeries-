package com.yusufaydin.populartvseries.viewmodel

import androidx.lifecycle.ViewModel
import com.yusufaydin.populartvseries.model.SeriesDetailModel
import com.yusufaydin.populartvseries.model.TvShowX
import com.yusufaydin.populartvseries.repository.SeriesRepository
import com.yusufaydin.populartvseries.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.xml.namespace.QName

@HiltViewModel
class SeriesDetailViewModel @Inject constructor(
    private val repository: SeriesRepository
):ViewModel(){
    suspend fun getSelectedSeries(qId: Int) : Resource<SeriesDetailModel>{
        return repository.getSeriesDetail(qId)
    }

}