package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
        //val ForgotPass=findViewById<TextView>(R.id.forgotbtn);
        //val googllbtn=findViewById<RelativeLayout>(R.id.btnGoogle);
        //val facebookbtn=findViewById<RelativeLayout>(R.id.btnFacebook);
        val signupbtn=findViewById<TextView>(R.id.txtSignup);

        var v=false;

        //Show & Hide Password :
        ShowHide.setOnClickListener(){
            if(v!=true){
                v=true;
                ShowHide.setBackgroundResource(R.drawable.hide);
                Password.transformationMethod=HideReturnsTransformationMethod.getInstance();
            }else
            {
                v=false;
                ShowHide.setBackgroundResource(R.drawable.view);
                Password.transformationMethod=PasswordTransformationMethod.getInstance();
            }
        }

        //Register Button :
        signupbtn.setOnClickListener(){
            val intent=Intent(this@LoginActivity,RegisterActivity::class.java);
            startActivity(intent);
        }

        //Login Button :
        btnLogin.setOnClickListener(){
            authentificateUser(Email.text.toString(),Password.text.toString());

        }

    }

    private fun authentificateUser(email:String,password:String){
        val intent=Intent(this@LoginActivity,MainActivity::class.java)
        intent.putExtra("UserName","Salaheddine");
        startActivity(intent)
    }

}