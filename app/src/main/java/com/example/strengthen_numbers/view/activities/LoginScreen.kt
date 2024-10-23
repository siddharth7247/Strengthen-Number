package com.example.strengthen_numbers.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.utils.NetworkState
import com.example.strengthen_numbers.viewmodels.LoginScreenViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicatorSpec
import com.google.android.material.progressindicator.IndeterminateDrawable
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginScreen : AppCompatActivity() {
    lateinit var edtMobile : TextInputEditText
    lateinit var btnContinue : MaterialButton
    lateinit var txtPhoneError : TextView
    lateinit var layout : ConstraintLayout
    lateinit var progressBar: ProgressBar
    val loginViewModel : LoginScreenViewModel by viewModels()
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

        progressBar = findViewById(R.id.progressBar)
        edtMobile = findViewById(R.id.edtMobileNo)
        btnContinue = findViewById(R.id.btnContinue)
        txtPhoneError = findViewById(R.id.txtError)
        layout = findViewById(R.id.main)
        btnContinue.setOnClickListener{
            if(edtMobile.text?.length!! < 10){
                txtPhoneError.visibility = View.VISIBLE
            }else {
               sendOtp()
            }
        }
    }
    fun sendOtp(){
        Log.d("Api Response", loginViewModel.response.toString())
        loginViewModel.btnClick(edtMobile.text.toString())

        loginViewModel.networkState.observe(this){response->
            var message = loginViewModel.message
            when(response){
                is NetworkState.Success ->{
                    btnContinue.setText(R.string.txt_continue)
                    progressBar.visibility = View.INVISIBLE
                    showSnakBar(message,false)
                    Thread{
                        Thread.sleep(2000)
                        intent = Intent(this,OtpScreen::class.java)
                        intent.putExtra("phoneNo",edtMobile.text)
                        startActivity(intent)
                    }.start()

                }
                is NetworkState.Loading ->{
                    btnContinue.text = null
                    progressBar.visibility = View.VISIBLE
                }
                is NetworkState.Error ->{
                    progressBar.visibility = View.INVISIBLE
                    btnContinue.setText(R.string.txt_continue)
                    showSnakBar(message,true)
                }
            }
        }
    }
    private fun showSnakBar(message: String,isError : Boolean){
        val view: View? = this.currentFocus
        if (view != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
        if(isError){
            val snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG).setBackgroundTint(resources.getColor(R.color.red)).setTextMaxLines(2)
            snackbar.show()
        }else{
            val snackbar = Snackbar.make(layout, message, Snackbar.LENGTH_LONG).setBackgroundTint(resources.getColor(R.color.darkGreen)).setTextMaxLines(2)
            snackbar.show()
        }

    }
}