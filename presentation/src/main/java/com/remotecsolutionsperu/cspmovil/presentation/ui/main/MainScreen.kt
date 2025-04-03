package com.remotecsolutionsperu.cspmovil.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.FeedComponent
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Red_Dark
import com.remotecsolutionsperu.cspmovil.presentation.utils.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.NewsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = hiltViewModel()
) {

    val newsList by viewModel.newsList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    if (isLoading) {
        BasicAlertDialog (
            onDismissRequest = { },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        color = Red_Dark
                    )
                }
            },
        )
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = "Noticias",
            style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        SwipeRefresh(
            state = SwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.refreshNewsList() },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    contentColor = Red_Dark,
                )
            }
        ) {
            LazyColumn(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.background)
                    .fillMaxSize()
            ) {
                items(newsList) { newsItem ->
                    FeedComponent(
                        modifier = Modifier.padding(
                            vertical = 8.dp,
                            horizontal = 16.dp
                        ),
                        image = newsItem.imageUrl,
                        title = newsItem.title,
                        content = newsItem.content,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen()
}
