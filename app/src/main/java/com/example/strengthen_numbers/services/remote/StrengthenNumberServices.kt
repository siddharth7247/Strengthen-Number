package com.example.strengthen_numbers.services.remote

import com.example.strengthen_numbers.models.OtpSent
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface StrengthenNumberServices {
    @POST("send-otp")
    suspend fun sendOtp(@Body  contact_number: JsonObject) : Response<OtpSent>
}