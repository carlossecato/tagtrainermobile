package com.example.tagtrainermobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import java.util.ArrayList

class ProductActivity : AppCompatActivity() {


     class Product(
             val name: String?,
             val quantity: String?
     ) : Parcelable {
         companion object {
             @JvmField
             val CREATOR = object : Parcelable.Creator<Product> {
                 override fun createFromParcel(parcel: Parcel) = Product(parcel)
                 override fun newArray(size: Int) = arrayOfNulls<Product>(size)
             }
         }
         private constructor(parcel: Parcel) : this(
                 name = parcel.readString(),
                 quantity = parcel.readString()
         )
         override fun writeToParcel(parcel: Parcel, flags: Int) {
             parcel.writeString(name)
             parcel.writeString(quantity)
         }
         override fun describeContents() = 0
    }

    var cartProducts: ArrayList<ProductActivity.Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        //var cartProductsBoundle = intent.getSerializableExtra("cart") as ArrayList<Product>
        val products = intent.getStringArrayExtra("products") as Array
        val idProduct =  intent.getIntExtra("id",34)
        val productImage = findViewById<ImageView>(R.id.productImageSrcID) as ImageView
        val textView = findViewById<TextView>(R.id.prodLabel) as TextView
        val productDesc = findViewById<TextView>(R.id.prodDescriptionId) as TextView
        val addProdButton = findViewById<Button>(R.id.buttonAddId) as Button

         fun addToCart (v: View, s: String): ArrayList<Product> {

            val productAdded = intent.getParcelableExtra<Product>("cart")

             Log.d("oi",productAdded.name.toString())
             cartProducts.add(productAdded)

             cartProducts.forEach { Log.d("oi",it.name)   }

            Snackbar.make(v, "Produto Adicionado ao carrinho: "+s, Snackbar.LENGTH_LONG).show()
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
                   cartProducts = addToCart(addProdButton, productName)

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
                   cartProducts = addToCart(addProdButton, productName)
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
                   cartProducts = addToCart(addProdButton, productName)
               }
           })

       }


    }
}