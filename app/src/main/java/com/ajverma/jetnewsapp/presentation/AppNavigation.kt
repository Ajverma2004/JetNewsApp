package com.ajverma.jetnewsapp.presentation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.ajverma.jetnewsapp.domain.util.AppScaffold
import com.ajverma.jetnewsapp.presentation.ui.screens.home.HomeScreen
import com.ajverma.jetnewsapp.presentation.ui.screens.home.HomeViewModel
import com.ajverma.jetnewsapp.presentation.ui.screens.News.NewsScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.ajverma.jetnewsapp.presentation.utils.enterTransition
import com.ajverma.jetnewsapp.presentation.utils.exitTransition
import com.ajverma.jetnewsapp.presentation.utils.popEnterTransition
import com.ajverma.jetnewsapp.presentation.utils.popExitTransition
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    viewModelHome: HomeViewModel
) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(
            route = "home",
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) {
            val categoryList = listOf("business","entertainment", "general", "health", "science", "sports", "technology")
            AppScaffold(
                isHomeScreen = true,
                title = "News App",
                navigationAction = {},
                navigationIcon = Icons.Default.ArrowBack,
                categoryList = categoryList,
                onCategoryClick = { selectedCategory ->
                    Log.d("MainActivity", "Category clicked: $selectedCategory")
                    try {
                        when (selectedCategory) {
                            "business" -> viewModelHome.getNewsByCategory("business")
                            "entertainment" -> viewModelHome.getNewsByCategory("entertainment")
                            "general" -> viewModelHome.getNewsByCategory("general")
                            "health" -> viewModelHome.getNewsByCategory("health")
                            "science" -> viewModelHome.getNewsByCategory("science")
                            "sports" -> viewModelHome.getNewsByCategory("sports")
                            "technology" -> viewModelHome.getNewsByCategory("technology")
                            else -> Log.e("MainActivity", "Unknown category: $selectedCategory")
                        }
                    } catch (e: Exception) {
                        Log.e("MainActivity", "Error handling category click: ${e.message}")
                    }
                }
            ) { paddingValues ->
                HomeScreen(viewModel = viewModelHome, modifier = paddingValues, navController = navController)
            }
        }

        composable(
            route = "News?url={url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType }),
            enterTransition = { enterTransition() },
            exitTransition = { exitTransition() },
            popEnterTransition = { popEnterTransition() },
            popExitTransition = { popExitTransition() }
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            NewsScreen(url = url, navController = navController)
        }
    }
}