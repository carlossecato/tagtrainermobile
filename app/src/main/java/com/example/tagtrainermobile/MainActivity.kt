package com.example.tagtrainermobile

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.ListProductsAdapter
import com.example.tagtrainermobile.models.ListingProduct


class MainActivity : AppCompatActivity() {

    var listingProducts = ListingProduct.SingleList.singleListInstance

    fun setListProducts() {
        if(listingProducts.size <= 0) {
            val imageView0 = ImageView(this)
            imageView0.setImageResource(R.drawable.p0)
            val prod0 = ListingProduct(imageView0, 1, "Uma fragrância sensual e vibrante perfeita para momentos especiais.", "Luna Radiante", 89.90, "Principal")
            listingProducts.add(prod0)
            val imageView1 = ImageView(this)
            imageView1.setImageResource(R.drawable.p1)
            val prod1 = ListingProduct(imageView1, 2, "Todo mundo tem um jeito único de se perfumar. Mas para aproveitar todo o potencial da fragrância", "Essencial Masculino", 129.90, "Principal")
            listingProducts.add(prod1)
            val imageView2 = ImageView(this)
            imageView2.setImageResource(R.drawable.p2)
            val prod2 = ListingProduct(imageView2, 3, "Desodorante Colônia Kaiak Urbe Masculino - 100ml", "Kaiak Urbe", 94.90, "Principal")
            listingProducts.add(prod2)
        } else return
    }

    fun onClickedProducts(v: ListView, p: Int) {
        val intent = Intent(applicationContext, ProductActivity::class.java)

        val params = Bundle()
        params.putInt("id", p)
        intent.putExtras(params)

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListProducts()
        displayListingPage()

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        val menu = findViewById<SearchView>(R.id.searchViewId)

        menu.setSearchableInfo(searchManager.getSearchableInfo(componentName))

    }

    fun filteredProductsList() : ArrayList<ListingProduct> {
        val listCategory = intent.getStringExtra("id")

        val categoryList = ArrayList<ListingProduct>()
            for(i in listingProducts) {
                if(i.listProdCat == listCategory) {
                    categoryList.add(i)
                } else {
                    return listingProducts
                }
            }
        return categoryList
    }

    fun displayListingPage() {

        val table: ListView = findViewById(R.id.tableID)

        val adapter = ListProductsAdapter(this, filteredProductsList())
        table.adapter = adapter
            table.setOnItemClickListener { parent, view, position, id ->
                onClickedProducts(table, position)
            }
    }
}

