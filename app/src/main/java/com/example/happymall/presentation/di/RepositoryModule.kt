package com.example.happymall.presentation.di

import com.example.happymall.data.repository.ShopRepositoryImpl
import com.example.happymall.data.repository.datasource.ShopLocalDataSource
import com.example.happymall.data.repository.datasource.ShopRemoteDataSource
import com.example.happymall.domain.repository.ShopRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesShopRepository(
        shopRemoteDataSource: ShopRemoteDataSource,
        localDataSource: ShopLocalDataSource
    ): ShopRepository {
        return ShopRepositoryImpl(shopRemoteDataSource, localDataSource)
    }

}