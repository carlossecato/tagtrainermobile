package com.example.tagtrainermobile.models

import android.widget.ImageView

data class ListingProduct (
        var listProdImg: ImageView,
        var listProdId: Int,
        var listProdDesc: String,
        var listProdName: String,
        var listProdPrice: Double
        ) {
    object SingleList {
    var singleListInstance = ArrayList<ListingProduct>()
    }
}