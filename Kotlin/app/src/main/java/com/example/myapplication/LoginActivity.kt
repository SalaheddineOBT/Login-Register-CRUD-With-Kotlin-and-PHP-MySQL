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

    lateinit var btnLogin:Button
    lateinit var Email:EditText
    lateinit var Password:EditText
    lateinit var ShowHide:ImageView
    lateinit var ForgotPass:TextView
    lateinit var googllbtn:RelativeLayout
    lateinit var facebookbtn:RelativeLayout
    lateinit var signupbtn:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin=findViewById<AppCompatButton>(R.id.btnLogin);
        Email=findViewById<EditText>(R.id.EmailInput);
        Password=findViewById<EditText>(R.id.PasswordInput);
        ShowHide=findViewById<ImageView>(R.id.showhidepasswordbtn);
        //ForgotPass=findViewById<TextView>(R.id.forgotbtn);
        //googllbtn=findViewById<RelativeLayout>(R.id.btnGoogle);
        //facebookbtn=findViewById<RelativeLayout>(R.id.btnFacebook);
        signupbtn=findViewById<TextView>(R.id.txtSignup);

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
            Email.text.clear()
            Password.text.clear()
        }
    }

    fun Alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@LoginActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}