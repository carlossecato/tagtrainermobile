package com.example.tagtrainermobile.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
        var name: String,
        var quantity: Int,
        var price: String
) : Parcelable {


    object SingleCart {
        var singleCartinstance = ArrayList<Product>()
    }
}
