package com.example.tagtrainermobile

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.Product
import com.google.android.material.snackbar.Snackbar

class ProductActivity : AppCompatActivity() {

    var cartProducts = Product.SingleCart.singleCartinstance

    fun  cartEmpty (): Boolean {
        return cartProducts.size <= 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

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

        val products = intent.getStringArrayExtra("products") as Array
        val idProduct = intent.getIntExtra("id", 34)
        val productImage = findViewById<ImageView>(R.id.productImageSrcID) as ImageView
        val textView = findViewById<TextView>(R.id.prodLabel) as TextView
        val productDesc = findViewById<TextView>(R.id.prodDescriptionId) as TextView
        val productPrice = findViewById<TextView>(R.id.prodPriceId) as TextView
        val addProdButton = findViewById<Button>(R.id.buttonAddId) as Button
        val cartButton = findViewById(R.id.cartButtonId) as ImageButton
        cartNotEmpty(cartButton)

        fun addToCart(v: View, s: String, p: String): ArrayList<Product> {
            val productAdded = Product(s, 1, p)
            if (cartProducts.contains(productAdded)) {
                assert(productAdded != null)
                cartProducts.map { if (it.name == s) it.quantity++  }
            } else {
                cartProducts.add(productAdded)
                cartNotEmpty(cartButton)
            }
            Snackbar.make(v, "Produto Adicionado ao carrinho: " + s, Snackbar.LENGTH_LONG).show()
            return cartProducts
        }

        if (idProduct == 0) {
            productImage.setImageResource(R.drawable.p0)
            productImage.visibility = View.VISIBLE

            val productName = products[0].split("\n")[0]
            textView.text = productName.toString()
            val productDescText = products[0].split("\n")[2]
            productDesc.text = productDescText.toString()
            val productPriceText = products[0].split("\n")[3].split("price:")[1]
            productPrice.text = productPriceText.toString()

            addProdButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    addToCart(addProdButton, productName, productPriceText)
                }
            })

        } else if (idProduct == 1) {
            productImage.setImageResource(R.drawable.p1)
            productImage.visibility = View.VISIBLE

            val productName = products[1].split("\n")[0]
            textView.text = productName.toString()
            val productDescText = products[1].split("\n")[2]
            productDesc.text = productDescText.toString()
            val productPriceText = products[1].split("\n")[3].split("price:")[1]
            productPrice.text = productPriceText.toString()


            addProdButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    addToCart(addProdButton, productName, productPriceText)
                }
            })

        } else {
            productImage.setImageResource(R.drawable.p2)
            productImage.visibility = View.VISIBLE

            val productName = products[2].split("\n")[0]
            val productDescText = products[2].split("\n")[2]
            productDesc.text = productDescText.toString()
            textView.text = productName.toString()
            val productPriceText = products[2].split("\n")[3].split("price:")[1]
            productPrice.text = productPriceText.toString()

            addProdButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    addToCart(addProdButton, productName, productPriceText)
                }
            })
        }
    }
}