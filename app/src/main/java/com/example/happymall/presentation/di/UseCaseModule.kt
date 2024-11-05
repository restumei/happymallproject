package com.example.happymall.presentation.di

import com.example.happymall.domain.repository.ShopRepository
import com.example.happymall.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {


    @Singleton
    @Provides
    fun providesCartUseCase(repository: ShopRepository): CartUseCase {
        return CartUseCase(repository)
    }

    @Singleton
    @Provides
    fun providesProductUseCase(repository: ShopRepository): ProductUseCase {
        return ProductUseCase(repository)
    }

    @Singleton
    @Provides
    fun providesAuthUseCase(repository: ShopRepository): LoginUseCase {
        return LoginUseCase(repository)
    }

}