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

class RegisterActivity : AppCompatActivity(),IVolley {

    private lateinit var emailtxt:EditText
    private lateinit var passwordtxt:EditText
    private lateinit var usernametxt:EditText
    private lateinit var confirmtxt:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister:AppCompatButton=findViewById(R.id.btnRegister)
        val btnSignin=findViewById<TextView>(R.id.txtSignin)
        emailtxt=findViewById(R.id.EmailInp)
        passwordtxt=findViewById(R.id.PasswordInp)
        usernametxt=findViewById(R.id.UsernameInput)
        confirmtxt=findViewById(R.id.ConfirmasswordInput)
        val btngll:RelativeLayout=findViewById(R.id.btnGgll)
        val btnfb:RelativeLayout=findViewById(R.id.btnFb)
        val showhideconfirm:ImageView=findViewById(R.id.showhidepassCbtn)
        val showhidepss:ImageView=findViewById(R.id.showhidepassbtn)

        var v1=false
        var v2=false

        //btn to Login Page :
        btnSignin.setOnClickListener(){
            val intent=Intent(this@RegisterActivity,LoginActivity::class.java)
            startActivity(intent)
        }

        //Toogle txt Type of confirm :
        showhideconfirm.setOnClickListener(){
            if(v1 != true){
                v1=true
                showhideconfirm.setBackgroundResource(R.drawable.hide)
                confirmtxt.transformationMethod= HideReturnsTransformationMethod.getInstance()
            } else {
                v1=false
                showhideconfirm.setBackgroundResource(R.drawable.view)
                confirmtxt.transformationMethod= PasswordTransformationMethod.getInstance()
            }
        }

        //toogle txt password type :
        showhidepss.setOnClickListener(){
            if(v2 != true){
                v2=true
                showhidepss.setBackgroundResource(R.drawable.hide)
                passwordtxt.transformationMethod= HideReturnsTransformationMethod.getInstance()
            } else {
                v2=false
                showhidepss.setBackgroundResource(R.drawable.view)
                passwordtxt.transformationMethod= PasswordTransformationMethod.getInstance()
            }
        }

        //btn Register Actions :
        btnRegister.setOnClickListener(){
            val username=usernametxt.text.toString()
            val email=emailtxt.text.toString().trim()
            val password=passwordtxt.text.toString().trim()
            val confirm=confirmtxt.text.toString().trim()
            if(password == confirm){
                MyVolleyRequest.getInstance(this@RegisterActivity,this@RegisterActivity)
                    .Register("http://10.0.2.2/API%20PHP/Operations/Register.php",username,email,confirm)
            }else{
                alert("Message Error : ","Confirm Password is Incorrect !")
            }
        }

    }

    override fun onResponse(response: String) {

        when(response) {
            "422" -> alert("Message Error :", "Pleas Fill all The Required Fields !")
            "401" -> alert("Message Error :", "Invalid Email Format !")
            "403" -> alert("Message Error :", "Your Password Must Be At Least 8 Characters !")
            "405" -> alert("Message Error :", "Your User Name Must Be At Least 3 Characters Long !")
            "409" -> alert("Message Error :", "Incorect Email Or Password !")
            "408" -> alert("Message Error :", "This Email Already Exist !")
            else -> {
                val builder = AlertDialog.Builder(this@RegisterActivity)
                builder.setTitle("Message Information :")
                builder.setMessage("You Have SuccessFully Registered .")
                builder.setPositiveButton("Ok", { dialogInterface: DialogInterface, i: Int ->
                    finish()
                }).create()
                builder.show()

                emailtxt.text.clear()
                usernametxt.text.clear()
                confirmtxt.text.clear()
                passwordtxt.text.clear()
            }
        }
    }

    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@RegisterActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}