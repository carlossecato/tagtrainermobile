package com.example.tagtrainermobile

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
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

        val pager = findViewById<ViewPager>(R.id.homeBannerId)
            pager.adapter = homeBannerAdapter(bannersHome)
    }

    fun setHomeBanners() {
        if(bannersHome.size <= 0) {
            val bannerImg0 = ImageView(this)
            bannerImg0.setImageResource(R.drawable.b1)
            val banner0 = Banners(bannerImg0, 1, "top_banner_1", "Summer Sale")
            bannersHome.add(banner0)
            val bannerImg1 = ImageView(this)
            bannerImg1.setImageResource(R.drawable.b2)
            val banner1 = Banners(bannerImg1, 2, "top_banner_2", "Outlet")
            bannersHome.add(banner1)
        } else return
    }

    private inner class homeBannerAdapter(val banners : ArrayList<Banners>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view: View = layoutInflater.inflate(R.layout.banner_pager_item,container, false) as ViewGroup
            val teste = view.findViewById<ViewPager>(R.id.bannerPagerId) as ImageView
                teste.setImageDrawable(banners[position].bannerImg.drawable)


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
}