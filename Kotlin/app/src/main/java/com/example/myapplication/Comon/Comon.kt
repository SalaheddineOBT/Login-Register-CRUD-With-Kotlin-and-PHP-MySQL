package com.example.myapplication.Comon

import com.example.myapplication.Remote.IMyAPI
import com.example.myapplication.Remote.RetrofitClient

object Comon {

    val BASE_URL="http://10.0.2.2/API%20PHP/Operations/"

    val api:IMyAPI
        get() = RetrofitClient.getClient(BASE_URL).create(IMyAPI::class.java)

}