package com.example.tagtrainermobile.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
        val name: String,
        val quantity: String
) : Parcelable {


    object SingleCart {
        var singleCartinstance = ArrayList<Product>()
    }
}
