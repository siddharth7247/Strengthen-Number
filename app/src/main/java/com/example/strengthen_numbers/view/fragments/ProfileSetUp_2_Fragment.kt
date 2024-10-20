package com.example.strengthen_numbers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.strengthen_numbers.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileSetUp_2_Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_profile_set_up_2_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val genderAutoCompleteTextView = view.findViewById<AutoCompleteTextView>(R.id.genderAutoCompleteTextView)
        val genders = arrayOf("Male", "Female", "Other")
        val adapter =ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, genders)
        genderAutoCompleteTextView.setAdapter(adapter)

        genderAutoCompleteTextView.setOnClickListener {
            genderAutoCompleteTextView.showDropDown()
        }
    }
}