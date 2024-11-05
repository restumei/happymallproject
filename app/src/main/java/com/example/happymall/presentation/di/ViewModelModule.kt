package com.example.happymall.presentation.di

import android.app.Application
import com.example.happymall.data.util.SharedPreference
import com.example.happymall.domain.usecase.*
import com.example.happymall.presentation.viewmodel.*
import com.example.happymall.presentation.viewmodel.login.LoginViewModel
import com.example.happymall.presentation.viewmodel.splashscreen.SplashViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {
    @Singleton
    @Provides
    fun providesProductDetailViewModel(cartUseCase: CartUseCase): ProductDetailViewModel {
        return ProductDetailViewModel(cartUseCase)
    }

    @Singleton
    @Provides
    fun providesCartViewModel(cartUseCase: CartUseCase): CartViewModel {
        return CartViewModel(cartUseCase)
    }

    @Singleton
    @Provides
    fun providesHomeViewModel(app: Application, productUseCase: ProductUseCase): HomeViewModel {
        return HomeViewModel(app, productUseCase)
    }


    @Singleton
    @Provides
    fun providesLoginViewModel(
        loginUseCase: LoginUseCase,
        sharedPreference: SharedPreference
    ): LoginViewModel {
        return LoginViewModel(loginUseCase, sharedPreference)
    }

    @Singleton
    @Provides
    fun providesSplashViewModel(sharedPreference: SharedPreference): SplashViewModel {
        return SplashViewModel(sharedPreference)
    }

}