package com.example.interviewnexmedis

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private lateinit var editTextSearch: EditText
    private lateinit var filterButton: ImageButton

    private val productList = mutableListOf(
        Product(
            imageUrl = "https://images.tokopedia.net/img/cache/700/VqbcmM/2021/12/9/9000a84b-4eb0-46e5-9d74-d76ce6f68e05.jpg",
            title = "ECG Simulator MS400 / ECG Multiparameter",
            description = "Phantom Simulator ECG 12 Lead Multi-Parameter ((Harga Tidak Include Kabel Temperature dan IBP))"
        ),
        Product(
            imageUrl = "https://images.tokopedia.net/img/cache/900/VqbcmM/2020/11/26/5008e3ae-2d3f-4145-84c6-e1d6fe93c591.jpg",
            title = "Ukur Suhu Tubuh Thermometer Digital",
            description = "Mudah di Baca, Tampilan LCD, Berbunyi \"Beep\" Ketika sudah selesai."
        ),
        Product(
            imageUrl = "https://down-id.img.susercontent.com/file/b4887f0cecd05d07dc8336a4e655fc4f",
            title = "NEW Digital EEG Machine 24 Channel Brain Electric Activity KT88-2400 Limited original",
            description = "KT88-2400 Digital Brain Electric Activity Mapping Analyze System 24 Channel EEG Machine PC Software"
        ),
        Product(
            imageUrl = "https://images.tokopedia.net/img/cache/900/VqbcmM/2024/6/27/efa3eae6-8724-4ced-b732-f06924ba540c.jpg",
            title = "Kursi Roda Elektrik Ekonomis Careindo 503 - Lite",
            description = "Ingin \"Bebas\" Bergerak Kemana Saja? Segera ganti Kursi Roda Manual anda dengan Kursi Roda Elektrik CAREINDO."
        ),
        Product(
            imageUrl = "https://images.tokopedia.net/img/cache/900/VqbcmM/2022/1/28/b112aff5-606f-40de-994d-53ae89bc5db4.jpg",
            title = "bibit jeruk santang madu berbuah",
            description = "bibit siap tanam kualitas unggul hasil sangat memuaskan genjah banyak buah biji kecil hasil okulasi berbuah"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        editTextSearch = findViewById(R.id.edit_text_search)
        filterButton = findViewById(R.id.button_filter)

        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter

        editTextSearch.setOnEditorActionListener { _, _, _ ->
            val query = editTextSearch.text.toString().trim()
            productAdapter.performSearch(query)
            true
        }

        filterButton.setOnClickListener {
            val showOnlyFavorites = !productAdapter.isFilteringByFavorites()
            productAdapter.toggleFavoriteFilter(showOnlyFavorites)
            updateFilterButtonImage(showOnlyFavorites)
        }

        updateFilterButtonImage(false)
    }

    private fun updateFilterButtonImage(showOnlyFavorites: Boolean) {
        if (showOnlyFavorites) {
            filterButton.setImageResource(R.drawable.filter64)
        } else {
            filterButton.setImageResource(R.drawable.filter_null)
        }
    }
}



