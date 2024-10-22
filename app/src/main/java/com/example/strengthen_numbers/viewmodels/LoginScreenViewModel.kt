package com.example.strengthen_numbers.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.strengthen_numbers.models.OtpSent
import com.example.strengthen_numbers.repository.LoginScreenRepository
import com.example.strengthen_numbers.utils.NetworkState
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(val repository: LoginScreenRepository) : ViewModel() {

    var response : MutableLiveData<Response<OtpSent>> = MutableLiveData()
    var networkState : MutableLiveData<NetworkState<OtpSent>> = MutableLiveData()
    var message : String = ""

    fun btnClick( number : String){
        networkState.value = NetworkState.Loading()
        val jsonObject= JsonObject()
        jsonObject.addProperty("contact_number", number )

        viewModelScope.launch {
            var result = repository.sendOtp(jsonObject)
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
}