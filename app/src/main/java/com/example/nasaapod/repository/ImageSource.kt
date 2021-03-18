package com.example.nasaapod.repository

import android.util.Log
import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.example.nasaapod.data.ImageResponse
import com.example.nasaapod.data.ImageResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

private const val STARTING_PAGE_INDEX = 1

class ImageSource (private val assetProvider: AssetProvider) : PagingSource<Int, ImageResponseItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageResponseItem> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return try {
            val jsonFile = assetProvider.getJsonDataFromAsset("images.json")
            if (jsonFile != null) {
                Log.i("data", jsonFile)
            }

            val listJsonType = object : TypeToken<ImageResponse>() {}.type

            var imageResponse: List<ImageResponseItem> = Gson().fromJson(jsonFile, listJsonType)

            LoadResult.Page(
                data = imageResponse,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (imageResponse.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}