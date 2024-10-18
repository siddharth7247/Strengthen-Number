package com.example.strengthen_numbers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.adapters.HobbyGridAdapter
import com.example.strengthen_numbers.models.IntrestModel


class ProfileSetUp_3_Fragment : Fragment() {

    lateinit var hobbyGridView : GridView
    lateinit var hobbyList : List<IntrestModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_set_up_3_, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hobbyGridView = view.findViewById(R.id.hobbyGridView)
        hobbyList = listOf(
            IntrestModel("Gym",R.drawable.ic_gym),
            IntrestModel("Yoga",R.drawable.ic_yoga),
            IntrestModel("Cardio",R.drawable.ic_cardio),
            IntrestModel("Workout",R.drawable.ic_home_workout),
            IntrestModel("Cycling",R.drawable.ic_cycling),
            IntrestModel("Zumba",R.drawable.ic_zumba),
            IntrestModel("Dieting",R.drawable.ic_diet),
            IntrestModel("Sport",R.drawable.ic_sports),
            IntrestModel("Running",R.drawable.ic_running))

        val hobbyAdapter = HobbyGridAdapter(hobbyList,requireContext())
        hobbyGridView.adapter = hobbyAdapter
    }

}