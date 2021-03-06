package com.example.tagtrainermobile

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.ListProductsAdapter
import com.example.tagtrainermobile.models.ListingProduct

var listingProducts = ListingProduct.SingleList.singleListInstance
var searchedItens = ArrayList<ListingProduct>()

class SearchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_searchable)

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
            doMySearch(query)
                setSearcheableConfiguration(query)
            }
        }

    }

    fun doMySearch(query : String) : ListingProduct? {
        Log.d("dasasdasd","sda")
        for (List in listingProducts) {
            if (List.listProdId.toString() == query) {
                Log.d("dasasdasd","sda")
                return List
            } else if (List.listProdName.contains(query)) {
                return List
            } else if (List.listProdDesc.contains(query)) {
                return List
            }
        }
        return null
    }

    fun setSearcheableConfiguration(query : String) {
        val searchResult = findViewById<TextView>(R.id.searchResultsId)
            searchResult.text = "Resultado de Busca: "+query
        val searchTable = findViewById<ListView>(R.id.tableResultsId)
        val productResult = doMySearch(query)
        if (productResult != null) {
            searchedItens.add(productResult)
        }
        val adapter = ListProductsAdapter(this, searchedItens)
        searchTable.adapter = adapter


    }
}