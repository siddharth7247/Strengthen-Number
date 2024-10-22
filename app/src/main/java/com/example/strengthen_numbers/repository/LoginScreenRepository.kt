package com.example.strengthen_numbers.repository

import android.util.Log
import com.example.strengthen_numbers.models.OtpSent
import com.example.strengthen_numbers.services.remote.StrengthenNumberServices
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject

class LoginScreenRepository @Inject constructor(val services: StrengthenNumberServices){

    var message : String = ""
    suspend fun sendOtp(number : JsonObject) : Response<OtpSent>{
        val result = services.sendOtp(number)
        Log.d("Result", result.toString())

        if(result.isSuccessful){
            Log.d("Api Res", result.body()!!.meta.message)
            message = result.body()!!.meta.message
            return result
        }else {
            val gson = Gson()
            val type = object : TypeToken<OtpSent>() {}.type
            var errorResponse: OtpSent? = gson.fromJson(result.errorBody()!!.charStream(), type)
            message = errorResponse!!.meta.message
            Log.d("Message",message)
            return result
        }
    }
}