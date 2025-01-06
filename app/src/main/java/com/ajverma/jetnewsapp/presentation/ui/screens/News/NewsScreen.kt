package com.ajverma.jetnewsapp.presentation.ui.screens.News

import android.webkit.WebView
import android.webkit.WebViewClient
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
    ) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        })
    }
}