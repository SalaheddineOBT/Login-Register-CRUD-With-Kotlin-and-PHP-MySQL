package com.example.myapplication.model

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

object RegistrationUtil {

    fun ValidateRegistraionInputs
    (
        username:String,
        email:String,
        password:String,
        confirmpass:String
    ):Boolean{
        var isvalid = false
        if(username.isNullOrEmpty() || email.isNullOrEmpty() || password.isNullOrEmpty() || confirmpass.isNullOrEmpty()) isvalid = false
        else if( !password.equals(confirmpass)) isvalid = false
        else isvalid = true

        return isvalid
    }

    fun toBackend(username: String,email: String,password: String,ctx:Context):Boolean{
        var isValide = false
        val url="http://172.16.1.47/API%20PHP/Operations/Authontification/Register.php"
        val params=HashMap<String,String>()
        params["username"]=username
        params["email"]=email
        params["password"]=password
        val jO= JSONObject(params as Map<*, *>)
        val rq: RequestQueue = Volley.newRequestQueue(ctx)
        val jor= JsonObjectRequest(Request.Method.POST,url,jO, Response.Listener { res->
            if(res.getString("success").equals("1")) isValide = true
            else isValide = false
        }, Response.ErrorListener { err->
            return@ErrorListener
        })
        rq.add(jor)

        return isValide
    }

}