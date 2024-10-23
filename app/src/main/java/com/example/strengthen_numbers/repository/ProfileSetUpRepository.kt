package com.example.strengthen_numbers.repository

import android.util.Log
import com.example.example.UserData
import com.example.example.UserResponce
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

    suspend fun setUpProfile(token : String?,userRes: UserResponce){
        val response = services.editProfile(userRes)

        if (response.isSuccessful) {
            val userResponse = response.body()
            Log.d("Userresponce", userResponse.toString())
        } else {
            val errorBody = response.errorBody()?.string()
            Log.e("API Error", "Response Error: $errorBody")
            // Handle the error accordingly
        }
    }

}