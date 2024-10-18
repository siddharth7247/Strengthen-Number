package com.example.strengthen_numbers.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chaos.view.PinView
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.R.id.otpView
import com.example.strengthen_numbers.R.id.txtOtpSent
import com.google.android.material.snackbar.Snackbar

class OtpScreen : AppCompatActivity() {
    lateinit var txtOtpSent : TextView
    lateinit var txtNotReciveOtp : TextView
    lateinit var btnResendOtp : Button
    lateinit var btnVerify : Button
    lateinit var otpView : PinView
    lateinit var txtPleaseEnterOtp : TextView
    lateinit var layout : ConstraintLayout

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otp_screen)

        txtOtpSent = findViewById(R.id.txtOtpSent)
        txtNotReciveOtp = findViewById(R.id.txtNotReciveOtp)
        txtPleaseEnterOtp = findViewById(R.id.txtPleaseEnterOtp)
        btnResendOtp = findViewById(R.id.btnResendOtp)
        btnVerify = findViewById(R.id.btnVerify)
        otpView = findViewById(R.id.otpView)
        layout = findViewById(R.id.main)

        var bundle : Bundle? = intent.extras
        val phoneNo = bundle?.get("phoneNo")
        txtOtpSent.setText("We have sent the verification code to your ${phoneNo} mobile number.")
        showSnakBar()
        sendOtp()

        btnResendOtp.setOnClickListener{
            btnResendOtp.setTextColor(resources.getColor(R.color.lightGreen))
            sendOtp()
        }

        btnVerify.setOnClickListener{
            if(otpView.text?.length!! < 4){
                txtPleaseEnterOtp.visibility = View.VISIBLE
            }else{
                intent = Intent(this,LocationPermissionScreen::class.java)
                startActivity(intent)
            }
        }
    }
    private fun sendOtp(){
        Log.d("Send Otp Method", "send otp called")
        btnResendOtp.setClickable(false)
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtNotReciveOtp.setText("Didn`t recevied OTP? " +millisUntilFinished / 1000)
            }
            override fun onFinish() {
                txtNotReciveOtp.setText("Didn`t recevied OTP? 0 sec")
                btnResendOtp.setTextColor(resources.getColor(R.color.primaryColor))
                btnResendOtp.setClickable(true)
            }
        }.start()
    }
    private fun showSnakBar(){
        val view: View? = this.currentFocus
        if (view != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
        }
        val snackbar = Snackbar.make(layout, "One Time Password(OTP) has been sent Scuccessfully", Snackbar.LENGTH_LONG).setBackgroundTint(resources.getColor(R.color.darkGreen)).setTextMaxLines(2)
        snackbar.show()
    }
}