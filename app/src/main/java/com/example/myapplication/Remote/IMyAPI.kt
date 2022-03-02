package com.example.myapplication.Remote

import com.example.myapplication.Model.APIresponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IMyAPI {

    @FormUrlEncoded
    @POST("Register.php")
    fun register(@Field("username") username:String, @Field("email") email:String, @Field("password") password:String): Call<APIresponse>

    @FormUrlEncoded
    @POST("Login.php")
    fun login(@Field("email") email:String, @Field("password") password:String): Call<APIresponse>

}