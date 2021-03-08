package com.example.tagtrainermobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.ListingProduct
import com.example.tagtrainermobile.models.Product
import com.google.android.material.snackbar.Snackbar

class ProductActivity : AppCompatActivity() {

    var cartProducts = Product.SingleCart.singleCartinstance
    var listingProducts = ListingProduct.SingleList.singleListInstance


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        fun  cartEmpty (): Boolean {
            return cartProducts.size <= 0
        }

        fun cartNotEmpty(button: ImageButton) {
            if (cartEmpty()) {
                return
            } else {
                button.visibility = ImageButton.VISIBLE
                button.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        val intent = Intent(applicationContext, CartActivity::class.java)
                        startActivity(intent)
                    }
                })
            }
        }

        val idProduct = intent.getIntExtra("id", 34)
        val productImage = findViewById<ImageView>(R.id.productImageSrcID) as ImageView
        val textView = findViewById<TextView>(R.id.prodLabel) as TextView
        val productDesc = findViewById<TextView>(R.id.prodDescriptionId) as TextView
        val productPrice = findViewById<TextView>(R.id.prodPriceId) as TextView
        val addProdButton = findViewById<Button>(R.id.buttonAddId) as Button
        val cartButton = findViewById(R.id.cartButtonId) as ImageButton
        cartNotEmpty(cartButton)

        fun addToCart(v: View, s: String, p: Double) {
            val productAdded = Product(s, 1, p)
            val existingProduct = cartProducts.find({it.name == s})
            if (existingProduct !== null) {
                cartProducts.forEach { if (it.name == s) {
                    it.price = it.price + p
                    it.quantity++
                }
                }
            } else {
                cartProducts.add(productAdded)
                cartNotEmpty(cartButton)
            }
            Snackbar.make(v, "Produto Adicionado ao carrinho: " + s, Snackbar.LENGTH_LONG).show()
        }


            productImage.setImageDrawable(listingProducts.get(idProduct).listProdImg.drawable)
            productImage.visibility = View.VISIBLE

            val productName = listingProducts.get(idProduct).listProdName
            textView.text = productName
            val productDescText = listingProducts.get(idProduct).listProdDesc
            productDesc.text = productDescText
            val productPriceText = listingProducts.get(idProduct).listProdPrice
            productPrice.text = "R$ "+productPriceText.toString()

            addProdButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    addToCart(addProdButton, productName, productPriceText)
                }
            })

    }
}