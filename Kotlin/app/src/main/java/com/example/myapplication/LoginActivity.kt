package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton

class LoginActivity : AppCompatActivity(),IVolley {

    lateinit var email:EditText
    lateinit var password:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin:AppCompatButton=findViewById(R.id.btnLogin)
        email=findViewById(R.id.EmailInput)
        password=findViewById(R.id.PasswordInput)
        val showHide=findViewById<ImageView>(R.id.showhidepasswordbtn)
        val forgotPass:TextView=findViewById(R.id.forgotbtn)
        val googllbtn:RelativeLayout=findViewById(R.id.btnGoogle)
        val facebookbtn:RelativeLayout=findViewById(R.id.btnFacebook)
        val signupbtn:TextView=findViewById(R.id.txtSignup)

        var v=false

        //Show & Hide Password :
        showHide.setOnClickListener(){
            if(v!=true){
                v=true
                showHide.setBackgroundResource(R.drawable.hide)
                password.transformationMethod=HideReturnsTransformationMethod.getInstance()
            }else
            {
                v=false
                showHide.setBackgroundResource(R.drawable.view)
                password.transformationMethod=PasswordTransformationMethod.getInstance()
            }
        }

        //Register Button :
        signupbtn.setOnClickListener(){
            val intent=Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        //Login Button :
        btnLogin.setOnClickListener(){
            val email:String =email.text.toString()
            val pass:String=password.text.toString()

            MyVolleyRequest.getInstance(this@LoginActivity,this@LoginActivity)
                .Login("http://10.0.2.2/API%20PHP/Operations/Login.php",email,pass)

        }
    }

    override fun onResponse(response: String) {
        when(response) {
            "422" -> alert("Message Error :", "Pleas Fill all The Required Fields !")
            "401" -> alert("Message Error :", "Invalid Email Format !")
            "403" -> alert("Message Error :", "Your Password Must Be At Least 8 Characters !")
            "409" -> alert("Message Error :", "Incorrect Email Or Password !")
            else -> {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("UserName", "" + response)
                startActivity(intent)

                email.text.clear()
                password.text.clear()
            }
        }
    }

    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@LoginActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}