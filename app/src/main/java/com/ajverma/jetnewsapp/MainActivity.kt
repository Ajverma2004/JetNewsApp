package com.ajverma.jetnewsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.ajverma.jetnewsapp.domain.util.AppScaffold
import com.ajverma.jetnewsapp.presentation.AppNavigation
import com.ajverma.jetnewsapp.presentation.ui.screens.home.HomeScreen
import com.ajverma.jetnewsapp.presentation.ui.screens.home.HomeViewModel
import com.ajverma.jetnewsapp.ui.theme.JetNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelHome: HomeViewModel by viewModels()
        enableEdgeToEdge()
        setContent {
            JetNewsAppTheme {
                AppNavigation(viewModelHome = viewModelHome)
            }
        }
    }
}
