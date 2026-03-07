package com.example.ecommercestore

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommercestore.databinding.ProductCardBinding
import com.example.ecommercestore.models.ProductResponse
import com.example.ecommercestore.models.Products


class ProductAdapter(private val list: List<ProductResponse>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    class ViewHolder(val binding: ProductCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ProductCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val product = list[position]

        holder.binding.productName.text = product.title
        holder.binding.productPrice.text = "$" + product.price
        holder.binding.productCategory.text = product.category.name
        holder.binding.root.setOnClickListener {




            val intent = Intent(holder.itemView.context, ProductDetails::class.java)
            intent.putExtra("name", product.title)
            intent.putExtra("id", product.id)
            holder.itemView.context.startActivity(intent)
        }

        val imageUrl = product.images.firstOrNull()

        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background) // Always good to have a placeholder
            .error(R.drawable.ic_launcher_foreground)       // Show this if the URL is broken
            .centerCrop() // Ensures the image fills the space correctly
            .into(holder.binding.productImage)


    }
}