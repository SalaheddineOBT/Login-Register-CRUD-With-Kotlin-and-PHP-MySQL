package com.example.myapplication.Remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{

    private var refrofit:Retrofit?=null

    fun getClient(baseUrl:String):Retrofit{
        if(refrofit == null){
            refrofit=Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return refrofit!!
    }

}