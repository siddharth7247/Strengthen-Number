package com.example.strengthen_numbers.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.adapters.ProfileScreenAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ProfilesetUpScreen : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var myAdapter: ProfileScreenAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileset_up_screen1)
        enableEdgeToEdge()
        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.viewPager)
        myAdapter = ProfileScreenAdapter(this)
        viewPager2.adapter = myAdapter

        TabLayoutMediator(tabLayout,viewPager2){
                tab : TabLayout.Tab,position :Int ->

            when(position){
                0 -> {
                    tab.text = "Chatsfgdgdfgfd"
                }
                1 ->{
                    tab.text = "Statusdfgdfgdfg"
                }
                2 ->{
                    tab.text = "Callsfdgdfgfdg"
                }

            }
        }.attach()
    }
}
