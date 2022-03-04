package com.example.myapplication.Remote

import com.example.myapplication.Model.APIResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface IMyAPI {


    @POST("Register.php")
    @Headers("Content-Type: application/json")
    fun register(@Field("username") username:String, @Field("email") email:String, @Field("password") password:String): Call<APIResponse>


    @Headers("Content-Type: application/json")
    @POST("Login.php")
    fun login(@Field("email") email:String,@Field("password") password:String): Call<APIResponse>

}