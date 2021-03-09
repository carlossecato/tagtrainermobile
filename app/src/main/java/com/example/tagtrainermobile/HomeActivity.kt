package com.example.tagtrainermobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.tagtrainermobile.models.Banners

class HomeActivity : AppCompatActivity() {

    var bannersHome = Banners.SingleBanner.singleBannerInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setHomeBanners()
        homeClickButton()
    }

    fun setHomeBanners() {
        val pager = findViewById<ViewPager>(R.id.homeBannerId)
        pager.adapter = homeBannerAdapter(bannersHome)
        if(bannersHome.size <= 0) {
            val bannerImg0 = ImageView(this)
            bannerImg0.setImageResource(R.drawable.b1)
            val banner0 = Banners(bannerImg0, 1, "top_banner_1", "Principal")
            bannersHome.add(banner0)
            val bannerImg1 = ImageView(this)
            bannerImg1.setImageResource(R.drawable.b2)
            val banner1 = Banners(bannerImg1, 2, "top_banner_2", "Principal")
            bannersHome.add(banner1)
        } else return

    }

    private inner class homeBannerAdapter(val banners : ArrayList<Banners>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view: View = layoutInflater.inflate(R.layout.banner_pager_item,container, false) as ViewGroup
            val teste = view.findViewById<ViewPager>(R.id.bannerPagerId) as ImageView
                teste.setImageDrawable(banners[position].bannerImg.drawable)
                teste.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(view: View?) {
                        onClickBannerAction(banners[position].promotion_name)
                    }
                })
            container.addView(view)
            return view
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }

        override fun getCount(): Int = 2

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view == `object`
        }

    }

    fun homeClickButton() {
        val button = findViewById<Button>(R.id.homeButtonId)
        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
        })
    }

    fun onClickBannerAction(p: String) {
        val intent = Intent(applicationContext, MainActivity::class.java)
        Log.d("oi   ",p)
        val params = Bundle()
        params.putString("id", p)
        intent.putExtras(params)

        startActivity(intent)
    }

}