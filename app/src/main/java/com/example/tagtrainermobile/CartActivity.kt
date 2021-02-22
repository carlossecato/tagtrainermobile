package com.example.tagtrainermobile

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.Product
import java.text.DecimalFormat


class CartActivity : AppCompatActivity() {

    var cartProducts = Product.SingleCart.singleCartinstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setTableForCartProducts()
        cartTotalPrice()

    }

    fun setTableForCartProducts() {

        val table: TableLayout = findViewById(R.id.tableCartId)
        val tableTitle = TextView(this)
        tableTitle.gravity = Gravity.CENTER_HORIZONTAL
        tableTitle.text = "Cart Products"
        tableTitle.textSize = 24.0F
        tableTitle.typeface = Typeface.DEFAULT_BOLD
        table.addView(tableTitle)

        val tableRowHeader = TableRow(this)
        val prodNameHeader = TextView(this)
        prodNameHeader.text = "Produto"
        prodNameHeader.textSize = 14.0F
        prodNameHeader.typeface = Typeface.DEFAULT_BOLD
        val prodQuantityHeader = TextView(this)
        prodQuantityHeader.text = "Qtd."
        prodQuantityHeader.textSize = 14.0F
        prodQuantityHeader.typeface = Typeface.DEFAULT_BOLD
        val prodPriceHeader = TextView(this)
        prodPriceHeader.text = "Preço"
        prodPriceHeader.textSize = 14.0F
        prodPriceHeader.typeface = Typeface.DEFAULT_BOLD
        tableRowHeader.addView(prodQuantityHeader)
        tableRowHeader.addView(prodNameHeader)
        tableRowHeader.addView(prodPriceHeader)
        table.addView(tableRowHeader)

        for (i in cartProducts.indices) {
            val tableRow = TableRow(this)
            val prodName = TextView(this)
            val prodQuantity = TextView(this)
            val prodPrice = TextView(this)
            val prodRemove = Button(this)

            tableRow.gravity = Gravity.CENTER_HORIZONTAL

            prodName.text = cartProducts.get(i).name
            prodQuantity.text = cartProducts.get(i).quantity.toString()
            val df = DecimalFormat("#.##")
            prodPrice.text = df.format(cartProducts.get(i).price).toString()
            //prodRemove.setBackgroundResource(R.drawable.img)

            tableRow.addView(prodQuantity)
            tableRow.addView(prodName)
            tableRow.addView(prodPrice)
            tableRow.addView(prodRemove)


            table.addView(tableRow)


            }

        val totalCartRow = TableRow(this)
        totalCartRow.gravity = Gravity.CENTER_HORIZONTAL

        val df = DecimalFormat("#.##")
        val emptySlot = TextView(this)
        emptySlot.text = ""
        val emptySlot2 = TextView(this)
        emptySlot2.text = ""
        val totalText = TextView(this)
        totalText.text = "Total"
        totalText.textSize = 14.0F
        totalText.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        totalText.typeface = Typeface.DEFAULT_BOLD
        val totalValue = TextView(this)
        totalValue.text = df.format(cartTotalPrice()).toString()
        totalValue.textSize = 24.0F
        totalValue.typeface = Typeface.DEFAULT_BOLD

        totalCartRow.addView(emptySlot)
        totalCartRow.addView(totalText)
        totalCartRow.addView(totalValue)
        totalCartRow.addView(emptySlot2)

        table.addView(totalCartRow)
    }

    fun cartTotalPrice() : Double {
        var totalValue : Double = 0.0
        val totalQuantity : Int = 0
        for(i in cartProducts.indices) {
            cartProducts.get(i).quantity
            totalValue = totalValue + cartProducts.get(i).price

        }
        return totalValue
    }
}

