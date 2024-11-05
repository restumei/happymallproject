package com.example.happymall.domain.usecase

import android.util.Log
import com.example.happymall.data.model.Login
import com.example.happymall.data.model.LoginResponse
import com.example.happymall.data.util.Resource
import com.example.happymall.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    fun loginUser(username: String, password: String): Flow<Resource<LoginResponse>> = flow {
        emit(Resource.Loading())
        try {
            val login = Login(username, password)
            val response = repository.loginUser(login)
            Log.i("AuthUseCase", "I dey here, ${response.data?.token}")
            emit(response)
        } catch (e: HttpException) {
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.i("AuthUseCase", "${e.localizedMessage}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

}