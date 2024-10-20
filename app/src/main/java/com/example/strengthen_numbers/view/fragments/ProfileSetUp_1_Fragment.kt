package com.example.strengthen_numbers.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.strengthen_numbers.R
import com.google.android.material.textfield.TextInputEditText

class ProfileSetUp_1_Fragment : Fragment() {

    lateinit var fullName : TextInputEditText
    lateinit var email : TextInputEditText
    lateinit var dateOfBirth : TextInputEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_set_up_1_, container, false)
        fullName = view.findViewById(R.id.edtFullName)
        email = view.findViewById(R.id.edtEmail)
        dateOfBirth = view.findViewById(R.id.dateOfBirth)
        return view
    }
    fun getInfo():PersonalInfo{
        val fullName = fullName.text.toString()
        val email = email.text.toString()
        val dateOfBirth = dateOfBirth.text.toString()
        return PersonalInfo(fullName,email,dateOfBirth)
    }

}

data class PersonalInfo(
    val fullName: String,
    val email: String,
    val dateOfBirth: String
)