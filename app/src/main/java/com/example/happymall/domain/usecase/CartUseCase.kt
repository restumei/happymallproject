package com.example.happymall.domain.usecase

import com.example.happymall.data.model.CartItem2
import com.example.happymall.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartUseCase @Inject constructor(
    private val repository: ShopRepository
) {

    suspend fun deleteCartItem(cartItem2: CartItem2) {
        repository.deleteCartItems(cartItem2)
    }

    suspend fun clearCart(): Int {
        return repository.clearCart()
    }

    fun getCartItems(): Flow<List<CartItem2>> {
        return repository.getCartItems()
    }

    suspend fun addToCartItem(cartItem2: CartItem2) {
        repository.addToCartItems(cartItem2)
    }

    suspend fun updateCartItem(cartItem2: CartItem2) {
        repository.updateCartItems(cartItem2)
    }

}