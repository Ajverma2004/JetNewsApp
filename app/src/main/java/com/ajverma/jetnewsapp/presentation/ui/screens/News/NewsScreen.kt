package com.ajverma.jetnewsapp.presentation.ui.screens.News

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.ajverma.jetnewsapp.domain.util.AppScaffold

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
    url: String,
    navController: NavController
) {
    AppScaffold(
        isHomeScreen = false,
        title = "News App",
        navigationIcon = Icons.Default.ArrowBack,
        navigationAction = {
            navController.popBackStack()
        }
    ) { paddingValues ->
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.apply {
                        javaScriptEnabled = true
                        domStorageEnabled = true // Enable DOM storage
                        useWideViewPort = true // Enable viewport adjustment
                        loadWithOverviewMode = true
                        cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK // Use cached resources
                        setSupportZoom(false)
                        builtInZoomControls = false
                        settings.blockNetworkLoads = true // Blocks unnecessary network loads

                    }
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            },
            modifier = paddingValues
        )
    }
}