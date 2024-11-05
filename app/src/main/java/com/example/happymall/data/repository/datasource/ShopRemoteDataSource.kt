package com.example.happymall.data.repository.datasource

import com.example.happymall.data.model.Cart
import com.example.happymall.data.model.CartItem
import com.example.happymall.data.model.Category
import com.example.happymall.data.model.Login
import com.example.happymall.data.model.LoginResponse
import com.example.happymall.data.model.Shop
import com.example.happymall.data.model.ShopItem
import retrofit2.Response

interface ShopRemoteDataSource {

    suspend fun getAllProducts(): Response<Shop>
    suspend fun getProduct(itemId: Int): Response<ShopItem>
    suspend fun getAllCategories(): Response<Category>
    suspend fun getCategoryProducts(category: String): Response<Shop>
    suspend fun uploadProduct(shopItem: ShopItem): Response<ShopItem>
    suspend fun updateProduct(id: Int, shopItem: ShopItem): Response<ShopItem>
    suspend fun deleteProduct(id: Int): Response<ShopItem>
    suspend fun getCart(id: Int): Response<Cart>
    suspend fun getCartProducts(id: Int): Response<CartItem>
    suspend fun addToCart(cartItem: CartItem): Response<CartItem>
    suspend fun updateCart(id: Int, cartItem: CartItem): Response<CartItem>
    suspend fun deleteCart(id: Int): Response<CartItem>
    suspend fun loginUser(login: Login): Response<LoginResponse>

}