package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.widget.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin=findViewById<AppCompatButton>(R.id.btnLogin);
        val Email=findViewById<EditText>(R.id.EmailInput);
        val Password=findViewById<EditText>(R.id.PasswordInput);
        val ShowHide=findViewById<ImageView>(R.id.showhidepasswordbtn);
        val ForgotPass=findViewById<TextView>(R.id.forgotbtn);
        val googllbtn=findViewById<RelativeLayout>(R.id.btnGoogle);
        val signupbtn=findViewById<TextView>(R.id.txtSignup);

        //Register Button :
        signupbtn.setOnClickListener(){
            val intent=Intent(this,RegisterActivity::class.java);
            startActivity(intent);
        }

        //Login Button :

        btnLogin.setOnClickListener(){

        }



    }
}