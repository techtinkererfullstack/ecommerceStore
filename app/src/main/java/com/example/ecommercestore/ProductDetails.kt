package com.example.ecommercestore

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.ecommercestore.databinding.ActivityProductDetailsBinding

class ProductDetails : AppCompatActivity() {
    var productId:Int = 0
    private lateinit var binding: ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        productId = intent.getIntExtra("id", productId)
        val name = intent.getStringExtra("name")
        val category = intent.getStringExtra("category")
        val image = intent.getStringExtra("image")
        val price = intent.getIntExtra("price", 0)
        val description = intent.getStringExtra("description")

        binding.productPrice.text = String.format("$$price")
        binding.productTitle.text = name
        binding.categoryBadge.text = category
        binding.productDescription.text = description

        val imageUrl = image

        // 3. Load Image
        Glide.with(this) // Use 'this' if in Activity, or binding.root.context
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .centerCrop()
            .into(binding.detailHeroImage)



    }
}