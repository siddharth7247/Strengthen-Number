package com.example.strengthen_numbers.utils

import android.os.Message

sealed class NetworkState < out T>{
    class Success<T> : NetworkState<T>()
    class Error<T>() : NetworkState<T>()
    class Loading<T> : NetworkState<T>()
}