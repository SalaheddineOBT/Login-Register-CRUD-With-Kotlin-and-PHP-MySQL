package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister=findViewById<Button>(R.id.btnRegister);
        val btnSignin=findViewById<TextView>(R.id.txtSignin);
        val emailtxt=findViewById<EditText>(R.id.EmailInp);
        val passwordtxt=findViewById<EditText>(R.id.PasswordInp);
        val usernametxt=findViewById<EditText>(R.id.UsernameInput);
        val confirmtxt=findViewById<EditText>(R.id.ConfirmasswordInput);
        //val btngll=findViewById<RelativeLayout>(R.id.btnGgll);
        val showhideconfirm=findViewById<ImageView>(R.id.showhidepassCbtn);
        val showhidepss=findViewById<ImageView>(R.id.showhidepassbtn);

        var v1=false;
        var v2=false;

        //btn to Login Page :
        btnSignin.setOnClickListener(){
            val intent=Intent(this@RegisterActivity,LoginActivity::class.java);
            startActivity(intent);
        }

        //Toogle txt Type of confirm :
        showhideconfirm.setOnClickListener(){
            if(v1!=true){
                v1=true;
                showhideconfirm.setBackgroundResource(R.drawable.hide);
                confirmtxt.transformationMethod= HideReturnsTransformationMethod.getInstance();
            }
            else
            {
                v1=false;
                showhideconfirm.setBackgroundResource(R.drawable.view);
                confirmtxt.transformationMethod= PasswordTransformationMethod.getInstance();
            }
        }

        //toogle txt password type :
        showhidepss.setOnClickListener(){
            if(v2!=true){
                v2=true;
                showhidepss.setBackgroundResource(R.drawable.hide);
                passwordtxt.transformationMethod= HideReturnsTransformationMethod.getInstance();
            }
            else
            {
                v2=false;
                showhidepss.setBackgroundResource(R.drawable.view);
                passwordtxt.transformationMethod= PasswordTransformationMethod.getInstance();
            }
        }

        //btn Register Actions :
        btnRegister.setOnClickListener(){
            if(passwordtxt.text.toString() == confirmtxt.text.toString()){
                authentificate(usernametxt.text.toString(),emailtxt.text.toString(),confirmtxt.text.toString())
            }else{
                Toast.makeText(this,"Confirm Password is Incorrect !",Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun authentificate(username: String, email: String, password: String) {

    }
}