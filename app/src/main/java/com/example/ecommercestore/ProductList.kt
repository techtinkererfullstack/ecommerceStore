package com.example.ecommercestore

import android.os.Bundle
import android.util.Log

import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommercestore.databinding.ActivityProductListBinding
import com.example.ecommercestore.models.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductList : AppCompatActivity() {
    private lateinit var binding: ActivityProductListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvProductList.layoutManager = GridLayoutManager(this, 2)

        loadProducts()
    }

    private fun loadProducts() {

        ApiClient.api.getProducts().enqueue(object : Callback<List<ProductResponse>> {

            override fun onResponse(
                call: Call<List<ProductResponse>>,
                response: Response<List<ProductResponse>>
            ) {

                try {
                    if (response.isSuccessful) {
                        val list = response.body() ?: emptyList()
                        binding.rvProductList.adapter = ProductAdapter(list)
                    } else {
                        Log.e("API_ERROR", "Code: ${response.code()}")
                    }
                } catch (e: Exception) {
                    Log.e("APP_CRASH", "Error parsing data: ${e.message}")
                    e.printStackTrace() // This will show you exactly what is failing in Logcat
                }

            }

            override fun onFailure(call: Call<List<ProductResponse>>, t: Throwable) {

                Toast.makeText(
                    this@ProductList,
                    t.message,
                    Toast.LENGTH_SHORT
                ).show()

            }

        })
    }
}