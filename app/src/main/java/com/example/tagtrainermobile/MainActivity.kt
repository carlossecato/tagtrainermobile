package com.example.tagtrainermobile

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val productList = arrayOf("Luna Radiante \nid:1" +
            "   \ndescription: Uma fragrância sensual e vibrante perfeita para momentos especiais.\n price:R$ 89,90",
            "Essencial Masculino \nid:2 " +
                    " \ndescription: Todo mundo tem um jeito único de se perfumar. Mas para aproveitar todo o potencial da fragrância\n price:R$ 129,90",
            "Kaiak Urbe \nid:3 " +
                    "  \ndescription: Desodorante Colônia Kaiak Urbe Masculino - 100ml\n price:R$ 94,90")

    //var cartProducts = ArrayList<SingleCart>()

    fun onClickedProducts(v: ListView, p: Int) {
        val intent = Intent(applicationContext, ProductActivity::class.java)

        val paramsProducts = Bundle()
        paramsProducts.putStringArray("products", productList)
        intent.putExtras(paramsProducts)

        val params = Bundle()
        params.putInt("id", p)
        intent.putExtras(params)

        startActivity(intent)
    }

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
        table.setOnItemClickListener { parent, view, position, id ->
            onClickedProducts(table, position)
        }
    }
}

