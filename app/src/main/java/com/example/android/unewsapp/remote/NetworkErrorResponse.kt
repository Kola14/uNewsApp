package com.example.android.unewsapp.remote

data class NetworkErrorResponse(val errors: ErrorObject)

data class ErrorObject(val _global: List<GlobalDescription>)

data class GlobalDescription(val code: String, val message: String)