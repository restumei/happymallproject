package com.example.happymall.data.repository.datasourceImpl

import com.example.happymall.data.db.ShopDAO
import com.example.happymall.data.model.CartItem2
import com.example.happymall.data.repository.datasource.ShopLocalDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopLocalDataSourceImpl @Inject constructor(
    private val shopDAO: ShopDAO
) : ShopLocalDataSource {
    override suspend fun addToCart(cartItem2: CartItem2) {
        return shopDAO.addToCart(cartItem2)
    }

    override fun getCartItems(): Flow<List<CartItem2>> {
        return shopDAO.cartItems()
    }

    override suspend fun updateCartItems(cartItem2: CartItem2) {
        return shopDAO.updateCart(cartItem2)
    }

    override suspend fun deleteCartItems(cartItem2: CartItem2) {
        return shopDAO.deleteCart(cartItem2)
    }

    override suspend fun clearCart(): Int {
        return shopDAO.clearAll() // This will return the number of deleted rows
    }

}