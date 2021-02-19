package com.example.tagtrainermobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
                        //addToCart(addProdButton, productName)
                    }
                })
            }
        }

        val products = intent.getStringArrayExtra("products") as Array
        val idProduct =  intent.getIntExtra("id",34)
        val productImage = findViewById<ImageView>(R.id.productImageSrcID) as ImageView
        val textView = findViewById<TextView>(R.id.prodLabel) as TextView
        val productDesc = findViewById<TextView>(R.id.prodDescriptionId) as TextView
        val addProdButton = findViewById<Button>(R.id.buttonAddId) as Button
        val cartButton = findViewById(R.id.cartButtonId) as ImageButton
            cartNotEmpty(cartButton)

         fun addToCart (v: View, s: String): ArrayList<Product> {
            val productAdded = Product(s,"1")
             cartProducts.add(productAdded)
             cartProducts.forEach { Log.d("oi",it.name)   }
             Snackbar.make(v, "Produto Adicionado ao carrinho: " + s, Snackbar.LENGTH_LONG).show()
             cartNotEmpty(cartButton)
            return cartProducts
        }

       if(idProduct == 0) {
           productImage.setImageResource(R.drawable.p0)
           productImage.visibility = View.VISIBLE

           val productName = products[0].split("\n")[0]
           textView.text = productName.toString()
           val productDescText = products[0].split("\n")[2]
           productDesc.text = productDescText.toString()

           addProdButton.setOnClickListener(object : View.OnClickListener {
               override fun onClick(view: View?) {
                   addToCart(addProdButton, productName)
               }
           })

       } else if (idProduct == 1){
           productImage.setImageResource(R.drawable.p1)
           productImage.visibility = View.VISIBLE

           val productName  = products[1].split("\n")[0]
           textView.text = productName.toString()
           val productDescText = products[1].split("\n")[2]
           productDesc.text = productDescText.toString()

           addProdButton.setOnClickListener(object : View.OnClickListener {
               override fun onClick(view: View?) {
                   addToCart(addProdButton, productName)
               }
           })

       } else {
           productImage.setImageResource(R.drawable.p2)
           productImage.visibility = View.VISIBLE

           val productName = products[2].split("\n")[0]
           val productDescText = products[2].split("\n")[2]
           productDesc.text = productDescText.toString()
           textView.text = productName.toString()

           addProdButton.setOnClickListener(object : View.OnClickListener {
               override fun onClick(view: View?) {
                   addToCart(addProdButton, productName)
               }
           })
       }
    }
}