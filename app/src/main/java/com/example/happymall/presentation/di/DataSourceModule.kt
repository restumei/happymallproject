package com.example.happymall.presentation.di

import com.example.happymall.data.api.ShopApiService
import com.example.happymall.data.db.ShopDAO
import com.example.happymall.data.repository.datasource.ShopLocalDataSource
import com.example.happymall.data.repository.datasource.ShopRemoteDataSource
import com.example.happymall.data.repository.datasourceImpl.ShopLocalDataSourceImpl
import com.example.happymall.data.repository.datasourceImpl.ShopRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providesLocalDataSource(shopDAO: ShopDAO): ShopLocalDataSource {
        return ShopLocalDataSourceImpl(shopDAO)
    }

    @Singleton
    @Provides
    fun provideShopRemoteDataSource(shopApiService: ShopApiService): ShopRemoteDataSource {
        return ShopRemoteDataSourceImpl(shopApiService)
    }

}