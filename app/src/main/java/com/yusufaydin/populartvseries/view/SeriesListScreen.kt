package com.yusufaydin.populartvseries.view


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.yusufaydin.populartvseries.model.TvShow
import com.yusufaydin.populartvseries.viewmodel.SeriesListViewModel

@Composable
fun SeriesListScreen(
    navController: NavController,
    viewModel:SeriesListViewModel = hiltViewModel(),
){

    Surface(
        color= Color.Black,
        modifier = Modifier.fillMaxSize(1f)
    ) {
        Column() {

            Text(text = "Popular TV Series", modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp, top = 10.dp),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
                )

            Spacer(modifier = Modifier.height(5.dp))
            SearchBar(
                hint = "Search ...", modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ){
                viewModel.searchSeriesList(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            SeriesList(navController = navController)
        }

    }

}

@Composable
fun SearchBar(
    modifier:Modifier=Modifier,
    hint:String="",
    onSearch:(String)->Unit={},
) {

    var text by remember{
        mutableStateOf("")
    }

    var isHintDisplayed by remember{
        mutableStateOf(hint!="")
    }
    Box(modifier = modifier) {

        BasicTextField(value =text , onValueChange ={
            text=it
            onSearch(it)
        },  maxLines = 1,
            singleLine = true,
            textStyle = TextStyle.Default,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                })
        if(isHintDisplayed){
            Text(text = hint,
                color = Color.LightGray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp))
        }
    }

}

@Composable
fun SeriesList(navController: NavController,
               viewModel:SeriesListViewModel= hiltViewModel()
) {
    val seriesList by remember{viewModel.seriesList}
    val errorMessage by remember{viewModel.errorMessage}
    var isLoading by remember{viewModel.isLoading}

    SeriesListView(series = seriesList, navController = navController)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        if(isLoading){
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
        if(errorMessage.isNotEmpty()){
            RetryView(error = errorMessage) {
                viewModel.loadSeries(1)
            }
        }
    }
}

@Composable
fun SeriesListView(series:List<TvShow>,navController: NavController,viewModel: SeriesListViewModel= hiltViewModel()) {
    val page=viewModel.page++
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(series){series->
            SeriesRow(navController = navController, series = series)
        }
        item {
            Button(onClick = {
                viewModel.loadSeries(page=page)
            }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray),
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(start = 10.dp, end = 10.dp, top = 2.dp)
                    .border(
                        0.dp, Color.DarkGray,
                        CircleShape
                    ), shape = CircleShape) {
                Text(text = "Load More Series", fontSize = 20.sp, color = Color.White,
                    textAlign = TextAlign.Center)
            } }
    }
}

@Composable
fun SeriesRow(navController: NavController,series:TvShow) {
    Row() {
        Image(
            painter= rememberImagePainter(data = series.imageThumbnailPath?:""),
            contentDescription = series.name, modifier = Modifier
                .size(100.dp,100.dp)
        )

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.secondary)
            .clickable {
                navController.navigate(
                    "series_detail_screen/${series.id}"
                )
            }) {
            Text(text = series.name,
                fontSize = 21.sp,
                modifier = Modifier.padding(1.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary)

            Text(text = series.country?:"",
                fontSize = 16.sp,
                modifier = Modifier.padding(1.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant)

            Text(text = series.status?:"",
                fontSize = 16.sp,
                modifier = Modifier.padding(1.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primaryVariant)

            Text(text = series.startDate?:"",
                fontSize = 14.sp,
                modifier = Modifier.padding(1.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary)

        }
    }

}

@Composable
fun RetryView(
    error:String,
    onRetry:()->Unit
) {
    Column() {
        Text(error, color = Color.Red, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            onRetry
        }, modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "Retry")
        }
    }
}