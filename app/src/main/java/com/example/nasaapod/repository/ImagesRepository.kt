package com.example.nasaapod.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class ImagesRepository @Inject constructor(private val assetProvider: AssetProvider) {

    fun getImages() = Pager(
    config = PagingConfig(
    pageSize = 20,
    maxSize = 100,
    enablePlaceholders = false
    ),
    pagingSourceFactory = { ImageSource(assetProvider) }
    ).liveData
}