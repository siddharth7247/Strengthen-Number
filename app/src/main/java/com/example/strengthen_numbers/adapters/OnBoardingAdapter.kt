package com.example.strengthen_numbers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.strengthen_numbers.R

class OnBoardingAdapter(val context: Context, val imgList : List<Int>)
    : PagerAdapter()
{

    override fun getCount(): Int {
        return imgList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imagLayout : View = LayoutInflater.from(context).inflate(R.layout.item_image,container,false)
        val imageView : ImageView = imagLayout.findViewById(R.id.img)
        imageView.setImageResource(imgList[position])
        container.addView(imagLayout)
        return imagLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}