package com.example.strengthen_numbers.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.example.UserData
import com.example.example.UserResponce
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.adapters.ProfileScreenAdapter
import com.example.strengthen_numbers.view.fragments.InterestsInfo
import com.example.strengthen_numbers.view.fragments.PersonalInfo
import com.example.strengthen_numbers.view.fragments.ProfileSetUp_1_Fragment
import com.example.strengthen_numbers.view.fragments.ProfileSetUp_3_Fragment
import com.example.strengthen_numbers.viewmodels.ProfileScreenViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfilesetUpScreen : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager2
    lateinit var pageAdapter: ProfileScreenAdapter
    lateinit var btnNext : MaterialButton
    lateinit var btnPrevious : MaterialButton
    lateinit var personalInfo : PersonalInfo
    lateinit var intrestInfo : InterestsInfo
    val profileViewModel : ProfileScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profileset_up_screen1)
        enableEdgeToEdge()
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        btnPrevious = findViewById(R.id.btnPrevious)
        btnNext = findViewById(R.id.btnNext)

        pageAdapter = ProfileScreenAdapter(this)
        viewPager.adapter = pageAdapter

        TabLayoutMediator(tabLayout,viewPager){
                tab : TabLayout.Tab,position :Int ->
        }.attach()
        viewPager.isUserInputEnabled = false

        var userShp = getSharedPreferences("UserData", MODE_PRIVATE)
        var token = userShp.getString("token","")
        Log.d("Token", token.toString())
        btnPrevious.setOnClickListener {
            if (viewPager.currentItem > 0) {
                viewPager.currentItem = viewPager.currentItem - 1
            }
        }

        btnNext.setOnClickListener {
            when (viewPager.currentItem) {
                0 -> {
                    val fragment = pageAdapter.getFragment(0) as ProfileSetUp_1_Fragment
                    val isValid = fragment.isValid()
                        if (isValid){
                            personalInfo = fragment.getInfo()
                            Log.d("Personal Info", personalInfo.toString())
                            var UserData = UserData(name = personalInfo.fullName, email = personalInfo.email, dob = personalInfo.dateOfBirth)
                            var userRes = UserResponce(UserData)
                            profileViewModel.setupProfile(token,userRes)
                            viewPager.currentItem = 1
                        }
                }
                1 -> viewPager.currentItem = 2
                2 -> {
                    val fragment = pageAdapter.getFragment(2) as ProfileSetUp_3_Fragment
                    intrestInfo = fragment.getInterestsInfo()
                    Log.d("Intrest Info", intrestInfo.toString())
                    Toast.makeText(this, intrestInfo.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        }

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButtonStates()
            }
        })
    }
    private fun updateButtonStates() {
        if(viewPager.currentItem == 0){
            btnPrevious.visibility = View.GONE
        }else {
            btnPrevious.visibility = View.VISIBLE
        }
        if (viewPager.currentItem == (viewPager.adapter?.itemCount ?: 0) - 1) {
            btnNext.text = "Submit"
            btnNext.setTextColor(resources.getColor(R.color.white))
        } else {
            btnNext.text = "Next"
        }
    }
}
