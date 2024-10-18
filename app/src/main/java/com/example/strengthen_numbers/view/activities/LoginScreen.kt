package com.example.strengthen_numbers.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.strengthen_numbers.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec
import com.google.android.material.progressindicator.IndeterminateDrawable
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginScreen : AppCompatActivity() {
    lateinit var edtMobile : TextInputEditText
    lateinit var btnContinue : MaterialButton
    lateinit var txtPhoneError : TextView
    lateinit var layout : ConstraintLayout
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtMobile = findViewById(R.id.edtMobileNo)
        btnContinue = findViewById(R.id.btnContinue)
        txtPhoneError = findViewById(R.id.txtError)
        layout = findViewById(R.id.main)

        btnContinue.setOnClickListener{
            if(edtMobile.text?.length!! < 10){
                txtPhoneError.visibility = View.VISIBLE
            }else {
                intent = Intent(this, OtpScreen::class.java)
                intent.putExtra("phoneNo",edtMobile.text)
                startActivity(intent)
            }
        }
    }
}