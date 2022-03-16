package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.*
import android.widget.*

class usermanager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usermanager)

        val titletext:TextView=findViewById(R.id.titletext)
        val btnuser:Button=findViewById(R.id.btnuser)
        val UsernamelIn:EditText=findViewById(R.id.UsernamelIn)
        val EmailIn:EditText=findViewById(R.id.EmailIn)
        val PasswordIn:EditText=findViewById(R.id.PasswordIn)
        val ConfirmasswordInp:EditText=findViewById(R.id.ConfirmasswordInp)
        val showp:ImageView=findViewById(R.id.showhidepassbt)
        val showc:ImageView=findViewById(R.id.showhidepassCbt)
        var methd:String=intent.getStringExtra("Methode").toString()

        titletext.text = methd
        btnuser.text = methd

        when(methd){
            "Add User" -> {
                btnuser.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.addicon),null,null,null)
            }
            "Update User" -> {
                btnuser.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.drawable.modificon),null,null,null)
            }
        }

        var v1=false
        var v2=false

        //Toogle txt Type of confirm :
        showc.setOnClickListener(){
            if(v1 != true){
                v1=true
                showc.setBackgroundResource(R.drawable.hide)
                ConfirmasswordInp.transformationMethod= HideReturnsTransformationMethod.getInstance()
            } else {
                v1=false
                showc.setBackgroundResource(R.drawable.view)
                ConfirmasswordInp.transformationMethod= PasswordTransformationMethod.getInstance()
            }
        }

        //toogle txt password type :
        showp.setOnClickListener(){
            if(v2 != true){
                v2=true
                showp.setBackgroundResource(R.drawable.hide)
                PasswordIn.transformationMethod= HideReturnsTransformationMethod.getInstance()
            } else {
                v2=false
                showp.setBackgroundResource(R.drawable.view)
                PasswordIn.transformationMethod= PasswordTransformationMethod.getInstance()
            }
        }

        btnuser.setOnClickListener{
            when(methd){
                "Add User" -> {

                }
                "Update User" -> {

                }
            }
        }



    }
}