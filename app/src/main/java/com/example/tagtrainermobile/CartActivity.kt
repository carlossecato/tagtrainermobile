package com.example.tagtrainermobile

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.Product




class CartActivity : AppCompatActivity() {

    var cartProducts = Product.SingleCart.singleCartinstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val table: ListView = findViewById(R.id.cartTableId)
        val inflater = layoutInflater
        val header = inflater.inflate(android.R.layout.list_content, table, false) as ViewGroup
        table.addHeaderView(header, null, false)


        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, cartProducts)
        table.adapter = arrayAdapter
    }
}