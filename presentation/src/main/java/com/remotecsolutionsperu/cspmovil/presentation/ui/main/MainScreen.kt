package com.remotecsolutionsperu.cspmovil.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.FeedComponent
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.NewsListViewModel

@Composable
fun MainScreen(
    feedList: List<String>,
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = hiltViewModel()
) {

    val newList by viewModel.newsList.collectAsState()

    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        items(newList) { newList ->
            FeedComponent(
                modifier = Modifier.padding(
                    vertical = 8.dp,
                    horizontal = 16.dp
                ),
                image = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                title = "Beca de Ingeniero Global, 60% de descuento",
                content = "Beneficio exclusivo para los ingenieros colegiados habilitados y familiares"
            )
        }
    }

}

@Preview
@Composable
private fun MainScreenPreview() {
    MainScreen(
        feedList = listOf("feed1", "feed2", "feed3")
    )
}
