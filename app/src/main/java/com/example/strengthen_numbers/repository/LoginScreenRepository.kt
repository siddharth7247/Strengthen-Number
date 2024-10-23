package com.example.strengthen_numbers.repository

import android.annotation.SuppressLint
import com.example.strengthen_numbers.models.OtpSent
import com.example.strengthen_numbers.services.remote.StrengthenNumberServices
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject

class LoginScreenRepository @Inject constructor(val services: StrengthenNumberServices){

    var message : String = ""
    var token : String = ""
    @SuppressLint("SuspiciousIndentation")
    suspend fun sendOtp(number : JsonObject) : Response<OtpSent> {
        val result = services.sendOtp(number)
            if (result.isSuccessful) {
                message = result.body()!!.meta.message
                return result
            } else {
                val gson = Gson()
                val type = object : TypeToken<OtpSent>() {}.type
                var errorResponse: OtpSent? = gson.fromJson(result.errorBody()!!.charStream(), type)
                message = errorResponse!!.meta.message
                return result
            }
    }

    suspend fun resendOtp(number : JsonObject) : Response<OtpSent>{
        val result = services.resendOtp(number)
        if(result.isSuccessful){
            message = result.body()!!.meta.message
            return result
        }else {
            val gson = Gson()
            val type = object : TypeToken<OtpSent>() {}.type
            var errorResponse: OtpSent? = gson.fromJson(result.errorBody()!!.charStream(), type)
            message = errorResponse!!.meta.message
            return result
        }
    }

}