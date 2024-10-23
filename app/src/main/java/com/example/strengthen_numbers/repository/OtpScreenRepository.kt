package com.example.strengthen_numbers.repository



import android.annotation.SuppressLint
import android.util.Log
import com.example.example.UserResponce
import com.example.strengthen_numbers.models.OtpSent
import com.example.strengthen_numbers.services.remote.StrengthenNumberServices
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Response
import javax.inject.Inject

class OtpScreenRepository @Inject constructor(val services: StrengthenNumberServices){

    var message : String = ""
    var token : String = ""
    @SuppressLint("SuspiciousIndentation")

    suspend fun verifyOtp(number : JsonObject) : Response<UserResponce>{
        val result = services.verifyOtp(number)
        if(result.isSuccessful){
            token = result.headers().get("X-Authorization-Token").toString()
            message = result.body()!!.meta!!.message.toString()
            return result
        }else {
            val gson = Gson()
            val type = object : TypeToken<OtpSent>() {}.type
            var errorResponse: OtpSent? = gson.fromJson(result.errorBody()!!.charStream(), type)
            Log.d("Message fro Repo", errorResponse!!.meta.message)
            message = errorResponse!!.meta.message
            Log.d("mess", message)
            return result
        }
    }
}