package com.example.happymall.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.happymall.data.model.CartItem2
import com.example.happymall.data.util.Utils
import com.example.happymall.domain.usecase.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.round

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {

    val totalItems: MutableLiveData<Int> = MutableLiveData()
    val totalItemsPrice: MutableLiveData<Double> = MutableLiveData()

    fun getCartItems() = liveData {
        cartUseCase.getCartItems().collect {
            emit(it)
            computeTotal(it)
        }
    }

    fun computeTotal(cartItems: List<CartItem2>) = viewModelScope.launch(Dispatchers.IO) {
        var price = 0.00
        cartItems.forEach { product ->
            // Pastikan harga produk bisa diubah menjadi Double
            val productPrice = product.price.replace(",", ".").toDoubleOrNull() ?: 0.0
            price += productPrice
        }
        // Post nilai total ke LiveData
        totalItemsPrice.postValue(round(price * 100) / 100) // Membulatkan harga ke 2 desimal
        totalItems.postValue(cartItems.size)
    }

    fun deleteCart(cartItem2: CartItem2) = viewModelScope.launch(IO) {
        cartUseCase.deleteCartItem(cartItem2)
    }

    fun clearCart() = viewModelScope.launch(IO) {
        cartUseCase.clearCart()
    }

    fun increment(cartItem: CartItem2) {
        val newPrice =
            cartItem.price.replace(",", ".").toDoubleOrNull()?.plus(cartItem.pricePerItem) ?: 0.0
        updateProductInCart(quantity = cartItem.quantity + 1, price = newPrice, cartItem)
    }

    fun decrement(cartItem: CartItem2) {
        if (cartItem.quantity > 1) {
            val newPrice =
                cartItem.price.replace(",", ".").toDoubleOrNull()?.minus(cartItem.pricePerItem)
                    ?: 0.0
            updateProductInCart(quantity = cartItem.quantity - 1, price = newPrice, cartItem)
        }
    }

    private fun updateProductInCart(quantity: Int, price: Double, cartItem: CartItem2) =
        viewModelScope.launch(Dispatchers.IO) {
            val formattedPrice = Utils.formatPrice(price.toString())
            val copy = cartItem.copy(price = formattedPrice, quantity = quantity)
            cartUseCase.updateCartItem(copy)
        }

}