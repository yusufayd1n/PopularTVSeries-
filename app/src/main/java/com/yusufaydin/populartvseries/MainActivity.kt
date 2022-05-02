package com.yusufaydin.populartvseries

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.yusufaydin.populartvseries.model.TvShow
import com.yusufaydin.populartvseries.ui.theme.PopularTVSeriesTheme
import com.yusufaydin.populartvseries.view.SeriesDetailScreen
import com.yusufaydin.populartvseries.view.SeriesListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PopularTVSeriesTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "series_list_screen"){
                    composable("series_list_screen"){
                        SeriesListScreen(navController = navController)
                    }
                    composable("series_detail_screen/{seriesId}", arguments = listOf(
                        navArgument("seriesId"){
                            type= NavType.IntType
                        }
                    )){
                        val seriesId= remember {
                            it.arguments?.getInt("seriesId")
                        }
                        SeriesDetailScreen(navController = navController, id = seriesId?:23455)

                    }
                }
            }
        }
    }
}

