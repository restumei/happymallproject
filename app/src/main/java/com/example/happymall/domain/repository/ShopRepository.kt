package com.example.happymall.domain.repository

import com.example.happymall.data.model.Cart
import com.example.happymall.data.model.CartItem
import com.example.happymall.data.model.CartItem2
import com.example.happymall.data.model.Category
import com.example.happymall.data.model.Login
import com.example.happymall.data.model.LoginResponse
import com.example.happymall.data.model.Product
import com.example.happymall.data.model.Shop
import com.example.happymall.data.model.ShopItem
import com.example.happymall.data.util.Resource
import kotlinx.coroutines.flow.Flow


interface ShopRepository {

    suspend fun getAllProducts(): Resource<Shop>
    suspend fun getProduct(itemId: Int): Resource<ShopItem>
    suspend fun getAllCategories(): Resource<Category>
    suspend fun getCategoryProducts(category: String): Resource<Shop>
    suspend fun uploadProduct(shopItem: ShopItem): Resource<ShopItem>
    suspend fun updateProduct(id: Int, shopItem: ShopItem): Resource<ShopItem>
    suspend fun deleteProduct(id: Int): Resource<ShopItem>
    suspend fun getCart(id: Int): Resource<Cart>
    suspend fun getCartProducts(id: Int): Resource<List<Product>>
    suspend fun addToCart(cartItem: CartItem): Resource<CartItem>
    suspend fun updateCart(id: Int, cartItem: CartItem): Resource<CartItem>
    suspend fun deleteCart(id: Int): Resource<CartItem>
    suspend fun loginUser(login: Login): Resource<LoginResponse>

    suspend fun addToCartItems(cartItem2: CartItem2)
    fun getCartItems(): Flow<List<CartItem2>>
    suspend fun updateCartItems(cartItem2: CartItem2)
    suspend fun deleteCartItems(cartItem2: CartItem2)
    suspend fun clearCart(): Int

}