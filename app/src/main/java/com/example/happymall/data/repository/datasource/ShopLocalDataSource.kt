package com.example.happymall.data.repository.datasource

import com.example.happymall.data.model.CartItem2
import kotlinx.coroutines.flow.Flow

interface ShopLocalDataSource {
    suspend fun addToCart(cartItem2: CartItem2)
    fun getCartItems(): Flow<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart(): Int

}