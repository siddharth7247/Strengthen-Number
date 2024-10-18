package com.example.strengthen_numbers.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.strengthen_numbers.view.fragments.ProfileSetUp_1_Fragment
import com.example.strengthen_numbers.view.fragments.ProfileSetUp_3_Fragment

class ProfileScreenAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ProfileSetUp_1_Fragment()
            1 -> ProfileSetUp_1_Fragment()
            2 -> ProfileSetUp_3_Fragment()
            else -> ProfileSetUp_1_Fragment()
        }
    }
}