package com.example.interviewnexmedis

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var filteredProductList = productList.toMutableList()
    private var showOnlyFavorites = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_card, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = filteredProductList[position]

        Picasso.get().load(product.imageUrl).placeholder(R.drawable.defprod).into(holder.productImage)

        holder.productTitle.text = product.title
        holder.productDescription.text = product.description

        if (product.isFavorite) {
            holder.favoriteButton.setImageResource(R.drawable.ic_favorite_border)
        } else {
            holder.favoriteButton.setImageResource(R.drawable.ic_favorite)
        }

        holder.favoriteButton.setOnClickListener {
            product.isFavorite = !product.isFavorite
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return filteredProductList.size
    }

    fun performSearch(query: String) {
        filteredProductList.clear()
        if (query.isEmpty()) {
            filteredProductList.addAll(productList)
        } else {
            val searchResults = productList.filter {
                it.title.contains(query, ignoreCase = true)
            }
            filteredProductList.addAll(searchResults)
        }
        notifyDataSetChanged()
    }

    fun toggleFavoriteFilter(showFavorites: Boolean) {
        showOnlyFavorites = showFavorites
        filterProducts()
    }

    private fun filterProducts() {
        filteredProductList.clear()
        if (showOnlyFavorites) {
            val favoriteProducts = productList.filter { it.isFavorite }
            filteredProductList.addAll(favoriteProducts)
        } else {
            filteredProductList.addAll(productList)
        }
        notifyDataSetChanged()
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productTitle: TextView = itemView.findViewById(R.id.product_title)
        val productDescription: TextView = itemView.findViewById(R.id.product_description)
        val favoriteButton: ImageButton = itemView.findViewById(R.id.favorite_button)
    }

    // Public method to check if currently filtering by favorites
    fun isFilteringByFavorites(): Boolean {
        return showOnlyFavorites
    }
}


