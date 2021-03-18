package com.example.nasaapod.repository

import android.content.Context
import java.io.IOException
import javax.inject.Inject

class AssetProvider @Inject constructor(
    private val context: Context
) {

    fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}
