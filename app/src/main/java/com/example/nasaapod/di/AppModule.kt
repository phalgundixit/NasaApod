package com.example.nasaapod.di

import android.content.Context
import com.example.nasaapod.repository.AssetProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesContext(@ApplicationContext context: Context): Context {
        return context
    }


    @Provides
    fun providesAssetProvider(@ApplicationContext context: Context): AssetProvider {
        return AssetProvider(context)
    }

}