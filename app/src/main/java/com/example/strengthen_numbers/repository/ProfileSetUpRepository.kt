package com.example.strengthen_numbers.repository

import android.util.Log
import com.example.example.UserData
import com.example.strengthen_numbers.services.remote.StrengthenNumberServices
import javax.inject.Inject

class ProfileSetUpRepository @Inject constructor(val services: StrengthenNumberServices){

//    suspend fun resendOtp(number : JsonObject) : Response<OtpSent>{
//        val result = services.resendOtp(number)
//        if(result.isSuccessful){
//            return result
//        }else {
//            val gson = Gson()
//            val type = object : TypeToken<OtpSent>() {}.type
//            var errorResponse: OtpSent? = gson.fromJson(result.errorBody()!!.charStream(), type)
//            return result
//        }
//    }

    suspend fun setUpProfile(userData: UserData){
        val result = services.editProfile(userData)
        if(result.isSuccessful){
            Log.d("User ProfileSetup", result.body().toString())
        }
    }

}