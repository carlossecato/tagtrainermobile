package com.example.tagtrainermobile

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.Product
import java.text.DecimalFormat

class PurchaseActivity : AppCompatActivity() {

    var cartProducts = Product.SingleCart.singleCartinstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purchase)
        cartTotalPrice()
        setRandomTransactionCode()
        setTransactionInfo()
        displayPurchaseItems()
    }

    fun cartTotalPrice() : Double {
        var totalValue: Double = 0.0
        val totalQuantity: Int = 0
        for (i in cartProducts.indices) {
            cartProducts.get(i).quantity
            totalValue = totalValue + cartProducts.get(i).price

        }
        return totalValue
    }

    fun setRandomTransactionCode() : String {
        val numbers = (0..40103430).random()
        val character = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val randChar = character.random()
        return numbers.toString()+randChar
    }

    fun setTransactionInfo() {
        val df = DecimalFormat("#.##")
        val txtTransactioId = findViewById<TextView>(R.id.transactioId)
            txtTransactioId.text = "Sua Compra: "+setRandomTransactionCode()
        val txtTransactionTotal = findViewById<TextView>(R.id.transactioTotalId)
            txtTransactionTotal.text = "Total: R$ "+df.format(cartTotalPrice())
    }

    fun displayPurchaseItems() {
        val purchaseTable = findViewById<ListView>(R.id.diplayPurchaseId)
        val listItems = arrayOfNulls<Product>(cartProducts.size)


        for (i in 0 until cartProducts.size) {
            val recipe : Product = cartProducts[i]
            listItems[i] = recipe
        }
        val adapter : ArrayAdapter<*>
            adapter = ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listItems)
        purchaseTable.adapter = adapter
    }


}