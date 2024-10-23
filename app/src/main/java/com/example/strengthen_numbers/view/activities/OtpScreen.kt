package com.example.strengthen_numbers.view.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.chaos.view.PinView
import com.example.strengthen_numbers.R
import com.example.strengthen_numbers.R.id.otpView
import com.example.strengthen_numbers.R.id.txtOtpSent
import com.example.strengthen_numbers.utils.NetworkState
import com.example.strengthen_numbers.viewmodels.OtpScreenViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class OtpScreen : AppCompatActivity() {
    lateinit var txtOtpSent : TextView
    lateinit var txtNotReciveOtp : TextView
    lateinit var btnResendOtp : Button
    lateinit var btnVerify : Button
    lateinit var otpView : PinView
    lateinit var txtPleaseEnterOtp : TextView
    lateinit var layout : ConstraintLayout
    lateinit var btnBack : ImageView
    val otpScreenViewModel : OtpScreenViewModel by viewModels()
    var phoneNo : Any? = 0
    lateinit var progressBar: ProgressBar
    lateinit var userDataPreferences: SharedPreferences

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
        btnBack = findViewById(R.id.btnBack)
        progressBar = findViewById(R.id.progressBar)

        val bundle : Bundle? = intent.extras
        phoneNo = bundle?.get("phoneNo")
        txtOtpSent.setText("We have sent the verification code to your ${phoneNo} mobile number.")
        startOTPTimer()

        btnResendOtp.setOnClickListener{
            btnResendOtp.setTextColor(resources.getColor(R.color.lightGreen))
            startOTPTimer()
            resendOtp()
        }

        btnVerify.setOnClickListener{
            if(otpView.text?.length!! < 4){
                txtPleaseEnterOtp.visibility = View.VISIBLE
            }else{
                verifyOtp()
            }
        }
//        btnBack.setOnClickListener{
//           var intent = Intent(this,LoginScreen::class.java)
//            startActivity(intent)
//        }
    }

    private fun startOTPTimer(){
        object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                btnResendOtp.setClickable(false)
                txtNotReciveOtp.setText("Didn`t recevied OTP? " +millisUntilFinished / 1000)
            }
            override fun onFinish() {
                txtNotReciveOtp.setText("Didn`t recevied OTP? 0 sec")
                btnResendOtp.setTextColor(resources.getColor(R.color.primaryColor))
                btnResendOtp.setClickable(true)
            }
        }.start()
    }

    fun resendOtp() {
        otpScreenViewModel.resendBtnClick(phoneNo.toString())
        Log.d("Resend Otp Phone No", phoneNo.toString())
        otpScreenViewModel.networkState.observe(this) { response ->
            when (response) {
                is NetworkState.Success -> {
                    btnVerify.setText(R.string.txt_verify)
                    progressBar.visibility = View.INVISIBLE
                    showSnakBar(otpScreenViewModel.message, false)
                }
                is NetworkState.Loading -> {
                    btnVerify.text = null
                    progressBar.visibility = View.VISIBLE
                }
                is NetworkState.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    btnVerify.setText(R.string.txt_continue)
                    showSnakBar(otpScreenViewModel.message, true)
                }
            }
        }
    }
    private fun verifyOtp() {
        otpScreenViewModel.verifyBtnClick(phoneNo.toString(),otpView.text.toString())
        otpScreenViewModel.userResponceState.observe(this) { userResponce ->
            when (userResponce) {
                is NetworkState.Success -> {
                    btnVerify.setText(R.string.txt_verify)
                    progressBar.visibility = View.INVISIBLE
                    saveUserToken(otpScreenViewModel.token)
                    showSnakBar(otpScreenViewModel.message, false)
                    Handler().postDelayed(
                        {
                            var locationPermsissionScreen = Intent(this,LocationPermissionScreen::class.java)
                            startActivity(locationPermsissionScreen)
                        }, 1500
                    )
                }
                is NetworkState.Loading -> {
                    btnVerify.text = null
                    progressBar.visibility = View.VISIBLE
                }
                is NetworkState.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    btnVerify.setText(R.string.txt_continue)
                    Log.d("Mess from view",otpScreenViewModel.otpMessage)
                    showSnakBar(otpScreenViewModel.otpMessage, true)
                }
            }
        }
    }
    private fun saveUserToken(token : String){
        userDataPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val userShp = userDataPreferences.edit()
        userShp.putString("token",token)
        userShp.apply()
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