package com.example.happymall.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)
