package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.*

class LoginActivity : AppCompatActivity(),IVolley {


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
            val email:String =Email.text.toString()
            val pass:String=Password.text.toString()
            MyVolleyRequest.getInstance(this@LoginActivity,this@LoginActivity)
                .postRequest("http://172.16.1.47/API%20PHP/Operations/Login.php","username",email,pass)
            Email.text.clear()
            Password.text.clear()
        }

    }

    override fun onResponse(response: String) {
        if(response=="422"){
            //Pleas Fill all The Required Fields !
            Alert("Message Error :","Pleas Fill all The Required Fields !")

        }else if(response=="401"){
             //Invalid Email Format !
            Alert("Message Error :","Invalid Email Format !")

        }else if(response=="403"){
            //Your Password Must Be At Least 8 Characters !
            Alert("Message Error :","Your Password Must Be At Least 8 Characters !")

        }else if(response=="409"){
            //Incorect Email Or Password !
            Alert("Message Error :","Incorect Email Or Password !")

        }else{
            val intent=Intent(this@LoginActivity,MainActivity::class.java)
            intent.putExtra("UserName",""+response);
            startActivity(intent)
        }
    }

    fun Alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@LoginActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int ->

        }).create()
        builder.show()
    }
}