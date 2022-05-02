package com.yusufaydin.populartvseries.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusufaydin.populartvseries.model.TvShow
import com.yusufaydin.populartvseries.repository.SeriesRepository
import com.yusufaydin.populartvseries.util.Constants.PAGE_SIZE
import com.yusufaydin.populartvseries.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesListViewModel @Inject constructor(
    private val repository: SeriesRepository
):ViewModel(){
    var seriesList= mutableStateOf<List<TvShow>>(listOf())
    var errorMessage= mutableStateOf("")
    var isLoading= mutableStateOf(false)
    var page : Int=1
    //serieslistmodel
    private var initialSeriesList= listOf<TvShow>()
    private var isSearchStarting=true

    init {
        loadSeries(page=page)
    }

    fun searchSeriesList(query:String){
        //make api request here
        val listToSearch = if(isSearchStarting){
            seriesList.value
        }else{
            initialSeriesList
        }

        viewModelScope.launch(Dispatchers.Default) {
            if(query.isEmpty()){
                seriesList.value=initialSeriesList
                isSearchStarting=true
                return@launch
            }

            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }

            if(isSearchStarting){
                initialSeriesList=seriesList.value
                isSearchStarting=false
            }

            seriesList.value=results

        }
    }


    fun loadSeries(page:Int){
        viewModelScope.launch {
            isLoading.value = true
            //work here
            val result = repository.getSeriesList(page)
            when(result){
                is Resource.Success->{
                    val seriesItems=result.data!!.tvShows.mapIndexed { index, tvShow ->
                        TvShow(tvShow.id,tvShow.name,tvShow.country,tvShow.imageThumbnailPath,tvShow.startDate,tvShow.status)
                    } //as List<TvShow>

                    errorMessage.value=""
                    isLoading.value=false
                    seriesList.value +=seriesItems


                }
                is Resource.Error->{
                    errorMessage.value=result.message!!
                    isLoading.value=false
                }
            }
        }

    }

}