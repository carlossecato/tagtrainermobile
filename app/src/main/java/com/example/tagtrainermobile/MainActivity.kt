package com.example.tagtrainermobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import  android.view.View
import android.widget.*
import androidx.core.view.get
import java.util.ArrayList



class MainActivity : AppCompatActivity() {

    val productList = arrayOf("Luna Radiante \nid:1" +
            "   \ndescription: Uma fragrância sensual e vibrante perfeita para momentos especiais.\n price:R$ 89,90",
            "Essencial Masculino \nid:2 " +
                    " \ndescription: Todo mundo tem um jeito único de se perfumar. Mas para aproveitar todo o potencial da fragrância\n price:R$ 129,90",
            "Kaiak Urbe \nid:3 " +
                    "  \ndescription: Desodorante Colônia Kaiak Urbe Masculino - 100ml\n price:R$ 94,90")
    var cartProducts: ArrayList<ProductActivity.Product> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val table: ListView = findViewById(R.id.tableID)
        val listItems = arrayOfNulls<String>(productList.size)

        for (i in 0 until productList.size) {
            val recipe = productList[i]
            listItems[i] = recipe

        }
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

        table.adapter = arrayAdapter

        fun onClickedProducts(v: ListView, p: Int) {
            // create a Toast with some text, to appear for a short time
            val myToast = Toast.makeText(this@MainActivity, "Hello Toast!"+p.toString(), Toast.LENGTH_SHORT)
            // show the Toast
            myToast.show()
            val intent = Intent(applicationContext, ProductActivity::class.java)
            val paramsProducts = Bundle()
            paramsProducts.putStringArray("products", productList)
            intent.putExtras(paramsProducts)
            val params = Bundle()
            params.putInt("id", p)
            intent.putExtras(params)
            val cart = Bundle()
            cart.putParcelableArrayList("cart", cartProducts)
            intent.putExtras(cart)

            startActivity(intent)
        }
        table.setOnItemClickListener { parent, view, position, id ->
            onClickedProducts(table, position)
        }
    }

}

