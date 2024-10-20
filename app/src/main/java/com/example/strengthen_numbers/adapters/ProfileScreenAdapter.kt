package com.example.strengthen_numbers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.strengthen_numbers.view.fragments.ProfileSetUp_1_Fragment
import com.example.strengthen_numbers.view.fragments.ProfileSetUp_2_Fragment
import com.example.strengthen_numbers.view.fragments.ProfileSetUp_3_Fragment

class ProfileScreenAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = mutableListOf<Fragment>()

    init {
        fragmentList.add(ProfileSetUp_1_Fragment())
        fragmentList.add(ProfileSetUp_2_Fragment())
        fragmentList.add(ProfileSetUp_3_Fragment())
    }

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> fragmentList[0]
            1 -> fragmentList[1]
            2 -> fragmentList[2]
            else -> fragmentList[0]
        }
    }

    fun getFragment(position: Int): Fragment{
        return fragmentList[position]
    }
}