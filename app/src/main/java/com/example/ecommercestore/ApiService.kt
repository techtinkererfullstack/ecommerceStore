package com.example.ecommercestore

import com.example.ecommercestore.models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    fun getProducts(): Call<List<ProductResponse>>

}