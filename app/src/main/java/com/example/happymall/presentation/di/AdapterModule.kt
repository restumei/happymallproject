package com.example.happymall.presentation.di

import com.example.happymall.presentation.ui.cart.adapter.CartAdapter
import com.example.happymall.presentation.ui.home.adapter.HomeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providesHomeAdapter(): HomeAdapter {
        return HomeAdapter()
    }

    @Singleton
    @Provides
    fun providesCartAdapter(): CartAdapter {
        return CartAdapter()
    }
}