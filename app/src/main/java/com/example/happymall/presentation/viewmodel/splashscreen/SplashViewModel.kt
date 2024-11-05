package com.example.happymall.presentation.viewmodel.splashscreen

import androidx.lifecycle.ViewModel
import com.example.happymall.data.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    sharedPreference: SharedPreference
) : ViewModel() {

    val isLogin: Boolean = sharedPreference.userIsLoggedIn()

}