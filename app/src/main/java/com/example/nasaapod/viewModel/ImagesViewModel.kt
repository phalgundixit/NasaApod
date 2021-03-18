package com.example.nasaapod.viewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nasaapod.data.ImageResponseItem
import com.example.nasaapod.repository.ImagesRepository


class ImagesViewModel@ViewModelInject constructor(
    private val repository: ImagesRepository,
    @Assisted state: SavedStateHandle
):ViewModel() {

    val images:LiveData<PagingData<ImageResponseItem>> = repository.getImages().cachedIn(viewModelScope)
}