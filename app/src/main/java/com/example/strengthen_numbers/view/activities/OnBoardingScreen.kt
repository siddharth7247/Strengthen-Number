package com.example.strengthen_numbers.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.adapters.OnBoardingAdapter
import com.google.android.material.button.MaterialButton
import me.relex.circleindicator.CircleIndicator


class OnBoardingScreen : AppCompatActivity() {
    lateinit var viewPager: ViewPager
    lateinit var myAdapter : OnBoardingAdapter
    lateinit var txtTitel : TextView
    lateinit var txtDese : TextView
    lateinit var btnNext : MaterialButton
    lateinit var btnSkip : MaterialButton
    lateinit var dotIndicator : CircleIndicator

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewPager = findViewById(R.id.viewPager1)
        txtTitel =  findViewById(R.id.txtTitle)
        txtDese = findViewById(R.id.txtDese)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)
        dotIndicator = findViewById(R.id.dotIndicator)

        var imgList: MutableList<Int> = mutableListOf()
        imgList.add(R.drawable.img_onboarding_1)
        imgList.add(R.drawable.img_onboarding_2)
        imgList.add(R.drawable.img_onboarding_3)
        myAdapter = OnBoardingAdapter(this,imgList)
        viewPager.adapter = myAdapter
        dotIndicator.setViewPager(viewPager)

        var titalList = listOf(R.string.header_onboarding_1,R.string.header_onboarding_2,R.string.header_onboarding_3)
        var deseList = listOf(R.string.dese_onboarding_1,R.string.dese_onboarding_2,R.string.dese_onboarding_3)

        txtTitel.setText(titalList[0])
        txtDese.setText(deseList[0])

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                txtTitel.setText(titalList[position])
                txtDese.setText(deseList[position])

                if (position == imgList.size - 1) {
                    btnSkip.visibility = TextView.GONE
                    btnNext.text = "Get Started"
                } else {
                    btnSkip.visibility = TextView.VISIBLE
                    btnNext.text = "Next"
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
        btnSkip.setOnClickListener{
            viewPager.setCurrentItem(3)
        }
        btnNext.setOnClickListener{
            val currentItem = viewPager.currentItem
            if(btnNext.text == "Next"){
                viewPager.setCurrentItem(currentItem+1)
            }else{
                intent = Intent(this,LoginScreen::class.java)
                startActivity(intent)
            }
        }
    }
}