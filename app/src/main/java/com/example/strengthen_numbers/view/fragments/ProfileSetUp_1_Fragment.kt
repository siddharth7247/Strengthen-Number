package com.example.strengthen_numbers.view.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.strengthen_numbers.R
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.Date

class ProfileSetUp_1_Fragment : Fragment() {

    lateinit var fullName : TextInputEditText
    lateinit var email : TextInputEditText
    lateinit var dateOfBirth : TextInputEditText
    lateinit var nameError : TextView
    lateinit var emaiError : TextView
    lateinit var dateOfBirthError : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        val view = inflater.inflate(R.layout.fragment_profile_set_up_1_, container, false)
        fullName = view.findViewById(R.id.edtFullName)
        email = view.findViewById(R.id.edtEmail)
        dateOfBirth = view.findViewById(R.id.dateOfBirth)
        nameError =  view.findViewById(R.id.nameError)
        emaiError = view.findViewById(R.id.emailError)
        dateOfBirthError = view.findViewById(R.id.dateOfBirthError)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dateOfBirth.setOnClickListener{
            GetDate()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun getInfo():PersonalInfo{
        val fullName = fullName.text.toString()
        val email = email.text.toString()
        val dateOfBirth = dateOfBirth.text.toString()
        return PersonalInfo(fullName,email,dateOfBirth)
    }
    fun GetDate(){
        val datePicker = MaterialDatePicker.Builder.datePicker().build()
        datePicker.show(childFragmentManager,"datePicker")
        datePicker.addOnPositiveButtonClickListener {
            val dateFormatter = SimpleDateFormat("dd-MM-yyyy")
            val date = dateFormatter.format(Date(it))
            dateOfBirth.setText(date)
        }
    }
    fun isValid():Boolean{
        if(fullName.text?.length == 0){
            nameError.visibility = View.VISIBLE
            return false
        }
        if(email.text?.length == 0){
            emaiError.visibility = View.VISIBLE
            return false
        }
        if(dateOfBirth.text?.length == 0){
            dateOfBirthError.visibility = View.VISIBLE
            return false
        }
        return true
    }
}

data class PersonalInfo(
    val fullName: String,
    val email: String,
    val dateOfBirth: String
)