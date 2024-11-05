package com.example.happymall.data.util

import com.example.happymall.data.model.ValidationResult
import java.text.NumberFormat
import java.util.Locale

object Utils {

    fun formatPrice(price: String): String {
        val numberFormat = NumberFormat.getInstance(Locale.US) // Ubah sesuai kebutuhan lokal
        val number = numberFormat.parse(price.replace(",", "")) // Menghapus pemisah ribuan jika ada
        return String.format("%.2f", number.toDouble())
    }

    fun validateLoginRequest(username: String, password: String): ValidationResult {
        if (username.isBlank() && password.isBlank()) return ValidationResult(
            false,
            "Username and password cannot be blank"
        )
        if (username.isBlank()) return ValidationResult(false, "Username cannot be blank")
        if (password.isBlank()) return ValidationResult(false, "Password cannot be blank")
        return ValidationResult(true)
    }

}