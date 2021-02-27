package com.example.tagtrainermobile

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.tagtrainermobile.models.ListProductsAdapter
import com.example.tagtrainermobile.models.ListingProduct


class MainActivity : AppCompatActivity() {

    val productList = arrayOf("Luna Radiante \nid:1" +
            "   \ndescription: Uma fragrância sensual e vibrante perfeita para momentos especiais.\n price:R$ 89.90",
            "Essencial Masculino \nid:2 " +
                    " \ndescription: Todo mundo tem um jeito único de se perfumar. Mas para aproveitar todo o potencial da fragrância\n price:R$ 129.90",
            "Kaiak Urbe \nid:3 " +
                    "  \ndescription: Desodorante Colônia Kaiak Urbe Masculino - 100ml\n price:R$ 94.90")

    var listingProducts = ListingProduct.SingleList.singleListInstance

    fun setListProducts() {
        if(listingProducts.size <= 0) {
            val imageView0 = ImageView(this)
            imageView0.setImageResource(R.drawable.p0)
            val prod0 = ListingProduct(imageView0, 1, "Uma fragrância sensual e vibrante perfeita para momentos especiais.", "Luna Radiante", 89.90)
            listingProducts.add(prod0)
            val imageView1 = ImageView(this)
            imageView1.setImageResource(R.drawable.p1)
            val prod1 = ListingProduct(imageView1, 2, "Todo mundo tem um jeito único de se perfumar. Mas para aproveitar todo o potencial da fragrância", "Essencial Masculino", 129.90)
            listingProducts.add(prod1)
            val imageView2 = ImageView(this)
            imageView2.setImageResource(R.drawable.p2)
            val prod2 = ListingProduct(imageView2, 3, "Desodorante Colônia Kaiak Urbe Masculino - 100ml", "Kaiak Urbe", 94.90)
            listingProducts.add(prod2)
            print(listingProducts.get(0).listProdName)
        } else return
    }

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
        setListProducts()
        displayListingPage()
    }



    fun displayListingPage() {
//        val teste : ImageView = findViewById(R.id.listingProdImgId)
//            teste.setImageResource(R.drawable.p0)
//        val teste : ListingProduct = ListingProduct(1, "Uma fragrância sensual e vibrante perfeita para momentos especiais.", "Luna Radiante", 89.90)
//        listingProducts.add(teste)


        val table: ListView = findViewById(R.id.tableID)
//        val listItems = arrayOfNulls<String>(productList.size)
//
//        for (i in 0 until productList.size) {
//            val recipe = productList[i]
//            listItems[i] = recipe
//
//        }

            val adapter = ListProductsAdapter(this, listingProducts)
//        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

            table.adapter = adapter
        table.setOnItemClickListener { parent, view, position, id ->
            onClickedProducts(table, position)
        }


    }
}

