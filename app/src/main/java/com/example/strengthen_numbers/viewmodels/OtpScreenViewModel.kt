package com.example.strengthen_numbers.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.UserResponce
import com.example.strengthen_numbers.models.OtpSent
import com.example.strengthen_numbers.models.UserData
import com.example.strengthen_numbers.repository.LoginScreenRepository
import com.example.strengthen_numbers.repository.OtpScreenRepository
import com.example.strengthen_numbers.utils.NetworkState
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class OtpScreenViewModel @Inject constructor(val repository: LoginScreenRepository,val otpRepository: OtpScreenRepository) : ViewModel() {

    var response : MutableLiveData<Response<OtpSent>> = MutableLiveData()
    var networkState : MutableLiveData<NetworkState<OtpSent>> = MutableLiveData()
    var userResponse : MutableLiveData<Response<UserResponce>> = MutableLiveData()
    var userResponceState :  MutableLiveData<NetworkState<UserResponce>> = MutableLiveData()
    var message : String = ""
    var token : String = ""
    var otpMessage = ""

    fun resendBtnClick( number : String){
        networkState.value = NetworkState.Loading()
        val jsonObject= JsonObject()
        jsonObject.addProperty("contact_number", number )

        viewModelScope.launch {
            var result = repository.resendOtp(jsonObject)
            if(result.isSuccessful){
                response.postValue(result)
                message = repository.message
                networkState.value = NetworkState.Success()
            }else{
                networkState.value = NetworkState.Error()
                message = repository.message
            }
        }
    }

    fun verifyBtnClick( number : String, otp : String){
        userResponceState.value = NetworkState.Loading()
        val jsonNumber= JsonObject()
        jsonNumber.addProperty("contact_number", number )
        jsonNumber.addProperty("otp",otp.toInt())

        viewModelScope.launch {
            var result = otpRepository.verifyOtp(jsonNumber)
            if(result.isSuccessful){
                if(result.body()?.data != null){
                    userResponse.postValue(result)
                    token = otpRepository.token
                    message = result.body()!!.meta!!.message.toString()
                    Log.d("Message from Viewmodel", message)
                    userResponceState.value = NetworkState.Success()
                }
            }else{
                userResponceState.value = NetworkState.Error()
                otpMessage = otpRepository.message
                Log.d("Mess from viewmodel", otpMessage)

            }
        }
    }
}