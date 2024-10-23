package com.example.strengthen_numbers.services.remote

import com.example.example.UserResponce
import com.example.strengthen_numbers.models.OtpSent
import com.example.strengthen_numbers.models.UserData
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HEAD
import retrofit2.http.Header
import retrofit2.http.POST


interface StrengthenNumberServices {
    @POST("send-otp")
    suspend fun sendOtp(@Body  contact_number: JsonObject) : Response<OtpSent>

    @POST("resend-otp")
    suspend fun resendOtp(@Body  contact_number: JsonObject) : Response<OtpSent>

    @POST("verify-otp")
    suspend fun verifyOtp(@Body  contact_number: JsonObject) : Response<UserResponce>

    @POST("edit-profile")
    suspend fun editProfile(@Body  userRes : UserResponce) : Response<UserResponce>


}