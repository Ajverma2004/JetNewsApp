package com.ajverma.jetnewsapp.domain.util


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    isHomeScreen: Boolean,
    title: String,
    navigationIcon: ImageVector? = null,
    navigationAction: (() -> Unit)? = null,
    categoryList: List<String> = emptyList(),
    onCategoryClick: ((String) -> Unit)? = null,
    screen: @Composable (paddingValues: Modifier) -> Unit,
) {

    var clicked by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    if (!isHomeScreen){
                        navigationIcon?.let {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable {
                                        navigationAction?.invoke()
                                    }
                                    .padding(start = 10.dp, end = 10.dp)
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 16.dp)

            ) {
                items(categoryList){ item ->
                    CategoryItem(
                        category = item,
                        onCategoryClick = { selectedCategory ->
                            onCategoryClick?.invoke(selectedCategory)
                        }
                    )
                }
            }
        }   
    ) { paddingValues ->
        screen(Modifier.padding(paddingValues))
    }
}

@Composable
private fun CategoryItem(
    category: String,
    onCategoryClick: ((String) -> Unit)? = null
) {
    var isSelected by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 0.92f else 1f,
        animationSpec = spring(dampingRatio = 0.7f)
    )

    Surface(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { 
                        isSelected = true
                        tryAwaitRelease()
                        isSelected = false
                        onCategoryClick?.invoke(category)
                    }
                )
            },
        color = MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = animateDpAsState(
            targetValue = if (isSelected) 8.dp else 2.dp,
            animationSpec = spring(dampingRatio = 0.7f)
        ).value
    ) {
        Text(
            text = category.capitalize(),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
