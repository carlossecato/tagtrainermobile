package com.example.tagtrainermobile

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
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
        searchedItens.clear()

        if (Intent.ACTION_SEARCH == intent.action) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
            doMySearch(query)
                setSearcheableConfiguration(query)
            }
        }

    }

    fun doMySearch(query : String) : ListingProduct? {
        for (List in listingProducts) {
            if (List.listProdId.toString() == query) {
                return List
            } else if (List.listProdName.contains(query, ignoreCase = true)) {
                return List
            } else if (List.listProdDesc.contains(query, ignoreCase = true)) {
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
            val adapter = ListProductsAdapter(this, searchedItens)
            searchTable.adapter = adapter
        } else {
            val prodNotFoundId = findViewById<TextView>(R.id.prodNotFoundId)
                prodNotFoundId.visibility = View.VISIBLE
                prodNotFoundId.text = "Produto não Encontrado!!"
            Log.d("prod","prodnao encontrado")
        }

    }
}