package com.example.strengthen_numbers.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.UserData
import com.example.strengthen_numbers.repository.LoginScreenRepository
import com.example.strengthen_numbers.repository.ProfileSetUpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileScreenViewModel @Inject constructor(val repository:ProfileSetUpRepository) : ViewModel() {

//    var response : MutableLiveData<Response<OtpSent>> = MutableLiveData()
//    var networkState : MutableLiveData<NetworkState<OtpSent>> = MutableLiveData()
//    var message : String = ""
//
//    fun btnClick( number : String){
//        networkState.value = NetworkState.Loading()
//        val jsonObject= JsonObject()
//        jsonObject.addProperty("contact_number", number )
//
//        viewModelScope.launch {
//            var result = repository.sendOtp(jsonObject)
//            if(result.isSuccessful){
//                response.postValue(result)
//                message = repository.message
//                networkState.value = NetworkState.Success()
//            }else{
//                networkState.value = NetworkState.Error()
//                message = repository.message
//            }
//        }
//    }

    fun setupProfile(userData: UserData){
        viewModelScope.launch {
            var result = repository.setUpProfile(userData)
            Log.d("Api res",result.toString())
        }
    }
}