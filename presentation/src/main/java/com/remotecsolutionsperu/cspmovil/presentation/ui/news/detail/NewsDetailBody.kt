package com.remotecsolutionsperu.cspmovil.presentation.ui.news.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.detail.NewsDetailViewModel

@Composable
fun NewsDetailBody(
    modifier: Modifier = Modifier,
    viewModel: NewsDetailViewModel,
) {
    val newsDetail by viewModel.newsDetail.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxSize(),
            model = newsDetail.image,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            text = newsDetail.title,
            fontWeight = FontWeight.Bold,
            style = Typography.bodyLarge,
            color = Color.Black
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            text = newsDetail.content,
            style = Typography.bodyMedium,
            color = Color.Black
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            text = newsDetail.description,
            style = Typography.bodyMedium,
            color = Color.Black
        )
    }
}