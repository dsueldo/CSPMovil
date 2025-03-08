package com.remotecsolutionsperu.cspmovil.presentation.ui.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.NewsListViewModel
import com.remotecsolutionsperu.cspmovil.presentation.ui.theme.Typography
import com.remotecsolutionsperu.cspmovil.presentation.ui.components.NewComponent

@Composable
fun NewsListScreen(
    newList: List<String>,
    restartApp: (String) -> Unit,
    openScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewsListViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Noticias",
            style = Typography.headlineMedium
        )

        LazyColumn(modifier = modifier) {
            items(newList) { new ->
                NewComponent(
                    modifier = Modifier.padding(bottom = 16.dp),
                    icon = "https://colegiodesociologosperu.org.pe/wp-content/uploads/2023/02/pronunciamiento-768x433.png",
                    discountText = "discountText",
                    expirationDateText = "expirationDateText",
                    conditionsText = "conditionsText"
                )
            }
        }
    }

}
