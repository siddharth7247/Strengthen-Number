package com.example.strengthen_numbers.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chaos.view.PinView
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.R.id.otpView
import com.example.strengthen_numbers.R.id.txtOtpSent

class OtpScreen : AppCompatActivity() {
    lateinit var txtOtpSent : TextView
    lateinit var txtNotReciveOtp : TextView
    lateinit var btnResendOtp : Button
    lateinit var btnVerify : Button
    lateinit var otpView : PinView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_otp_screen)

        txtOtpSent = findViewById(R.id.txtOtpSent)
        txtNotReciveOtp = findViewById(R.id.txtNotReciveOtp)
        btnResendOtp = findViewById(R.id.btnResendOtp)
        btnVerify = findViewById(R.id.btnVerify)
        otpView = findViewById(R.id.otpView)

        var bundle : Bundle? = intent.extras
        val phoneNo = bundle?.get("phoneNo")
        txtOtpSent.setText("We have sent the verification code to your ${phoneNo} mobile number.")
        sendOtp()

        btnResendOtp.setOnClickListener{
            btnResendOtp.setTextColor(resources.getColor(R.color.lightGreen))
            sendOtp()
        }
        btnVerify.setOnClickListener{
            if(otpView.text == null){
                Toast.makeText(this, "Please Enter ot", Toast.LENGTH_SHORT).show()
            }else{
                intent = Intent(this,LocationPermissionScreen::class.java)
                startActivity(intent)
            }
        }
    }
    fun sendOtp(){
        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtNotReciveOtp.setText("Didn`t recevied OTP? " +millisUntilFinished / 1000)
            }
            override fun onFinish() {
                txtNotReciveOtp.setText("Didn`t recevied OTP? 0 sec")
                btnResendOtp.setTextColor(resources.getColor(R.color.primaryColor))
            }
        }.start()
    }
}