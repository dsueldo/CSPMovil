package com.remotecsolutionsperu.cspmovil.presentation.viewmodels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.remotecsolutionsperu.cspmovil.domain.repositories.NewsRepository
import com.remotecsolutionsperu.cspmovil.presentation.viewmodels.news.NewsListViewModel
import javax.inject.Inject

class NewsListViewModelFactory @Inject constructor(
    private val newsRepository: NewsRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewsListViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}