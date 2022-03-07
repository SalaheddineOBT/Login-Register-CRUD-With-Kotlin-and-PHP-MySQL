package com.example.myapplication

import android.content.*
import androidx.appcompat.app.*
import android.os.Bundle
import android.text.method.*
import android.widget.*
import androidx.appcompat.widget.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister:AppCompatButton=findViewById(R.id.btnRegister)
        val btnSignin:TextView=findViewById(R.id.txtSignin)
        val emailtxt:EditText=findViewById(R.id.EmailInp)
        val passwordtxt:EditText=findViewById(R.id.PasswordInp)
        val usernametxt:EditText=findViewById(R.id.UsernameInput)
        val confirmtxt:EditText=findViewById(R.id.ConfirmasswordInput)
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
                emailtxt.text.clear()
                usernametxt.text.clear()
                confirmtxt.text.clear()
                passwordtxt.text.clear()
            }else{
                alert("Message Error : ","Confirm Password is Incorrect !")
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