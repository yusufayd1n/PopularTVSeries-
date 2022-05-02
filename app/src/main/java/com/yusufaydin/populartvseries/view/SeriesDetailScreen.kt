package com.yusufaydin.populartvseries.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.yusufaydin.populartvseries.model.SeriesDetailModel
import com.yusufaydin.populartvseries.model.TvShowX
import com.yusufaydin.populartvseries.util.Resource
import com.yusufaydin.populartvseries.viewmodel.SeriesDetailViewModel

@Composable
fun SeriesDetailScreen(
    navController: NavController,
    id:Int,
    viewModel:SeriesDetailViewModel= hiltViewModel()
){
    var seriesItem by remember{
        mutableStateOf<Resource<SeriesDetailModel>>(Resource.Loading())
    }

    LaunchedEffect(key1 = Unit){
        seriesItem=viewModel.getSelectedSeries(id)

    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        contentAlignment = Alignment.TopCenter) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
            when(seriesItem){
                is Resource.Success->{
                    LazyColumn(contentPadding = PaddingValues(5.dp), horizontalAlignment = Alignment.CenterHorizontally){
                        item {
                            val selectedSeries=seriesItem.data!!.tvShow
                            Text(text = selectedSeries.name, color = Color.White,
                                fontSize = 50.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 5.dp))
                            Row(modifier = Modifier.padding(bottom = 5.dp)) {
                                Image(
                                    painter= rememberImagePainter(data = selectedSeries.imageThumbnailPath),
                                    contentDescription = selectedSeries.name, modifier = Modifier
                                        .size(200.dp,200.dp)
                                )
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = "Genres:"+selectedSeries.genres.toString(), color =MaterialTheme.colors.secondary,
                                        fontSize = 17.sp, textAlign = TextAlign.Center)
                                    Text(text = "Runtime:"+selectedSeries.runtime.toString()+" minute", color =MaterialTheme.colors.secondary,
                                        fontSize = 17.sp, textAlign = TextAlign.Center)
                                    Text(text ="Number of Episodes :"+ selectedSeries.episodes.size,color =MaterialTheme.colors.secondary,
                                        fontSize = 17.sp, textAlign = TextAlign.Center)
                                    Text(text ="Rating out of 10 :"+ selectedSeries.rating, color =MaterialTheme.colors.secondary,
                                        fontSize = 17.sp, textAlign = TextAlign.Center)
                                }
                            }
                            Text(text = selectedSeries.description, color =MaterialTheme.colors.secondary,
                                fontSize = 15.sp, textAlign = TextAlign.Center)

                            Image(
                                painter= rememberImagePainter(data = selectedSeries.pictures[0]),
                                contentDescription = selectedSeries.name, modifier = Modifier
                                    .size(200.dp,200.dp)
                            )
                            Image(
                                painter= rememberImagePainter(data = selectedSeries.pictures[1]),
                                contentDescription = selectedSeries.name, modifier = Modifier
                                    .size(200.dp,200.dp)
                            )
                            Image(
                                painter= rememberImagePainter(data = selectedSeries.pictures[2]),
                                contentDescription = selectedSeries.name, modifier = Modifier
                                    .size(200.dp,200.dp)
                            )
                            Image(
                                painter= rememberImagePainter(data = selectedSeries.pictures[3]?:""),
                                contentDescription = selectedSeries.name, modifier = Modifier
                                    .size(200.dp,200.dp)
                            )
                            Image(
                                painter= rememberImagePainter(data = selectedSeries.pictures[4]?:""),
                                contentDescription = selectedSeries.name, modifier = Modifier
                                    .size(200.dp,200.dp)
                            )
                            Image(
                                painter= rememberImagePainter(data = selectedSeries.pictures[5]?:""),
                                contentDescription = selectedSeries.name, modifier = Modifier
                                    .size(200.dp,200.dp)
                            )


                        }
                    }
                }
            }

        }
    }
}