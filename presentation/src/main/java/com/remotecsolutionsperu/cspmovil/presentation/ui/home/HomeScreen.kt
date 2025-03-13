package com.remotecsolutionsperu.cspmovil.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.NewsListViewModel
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.FeedComponent

@Composable
fun HomeScreen(
    feedList: List<String>,
    modifier: Modifier = Modifier,
    newsListViewModel: NewsListViewModel,
    token: String
) {

    LaunchedEffect(Unit) {
    }

    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (uiState.error != null) {
            Text(text = "Error: ${uiState.error}", color = MaterialTheme.colorScheme.error)
        } else if (uiState.isSuccess) {
            Text(text = "Welcome, ${uiState.userProfile?.fullName}")
            // Display other user profile information
        }
    }

    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(feedList) { feed ->
            FeedComponent(
                image = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                title = "Beca de Ingeniero Global, 60% de descuento",
                content = "Beneficio exclusivo para los ingenieros colegiados habilitados y familiares"
            )
        }
    }*/
}