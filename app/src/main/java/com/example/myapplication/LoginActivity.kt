package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.widget.*
import com.example.myapplication.Comon.Comon
import com.example.myapplication.Model.APIresponse
import com.example.myapplication.Remote.IMyAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    internal lateinit var mService:IMyAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mService=Comon.api;

        val btnLogin=findViewById<AppCompatButton>(R.id.btnLogin);
        val Email=findViewById<EditText>(R.id.EmailInput);
        val Password=findViewById<EditText>(R.id.PasswordInput);
        val ShowHide=findViewById<ImageView>(R.id.showhidepasswordbtn);
        //val ForgotPass=findViewById<TextView>(R.id.forgotbtn);
        //val googllbtn=findViewById<RelativeLayout>(R.id.btnGoogle);
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
            val intent=Intent(this,RegisterActivity::class.java);
            startActivity(intent);
        }

        //Login Button :
        btnLogin.setOnClickListener(){
            authentificateUser(Email.text.toString(),Password.text.toString());
            //Toast.makeText(this@LoginActivity,Password.text.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    private fun authentificateUser(email:String,password:String){
        mService.login(email,password)
            .enqueue(object :Callback<APIresponse>{

                override fun onResponse(call: Call<APIresponse>, response: Response<APIresponse>) {
                    if(response!!.body()!!.success == 0){
                        Toast.makeText(this@LoginActivity,response.body()!!.message,Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this@LoginActivity,"Login Successed !",Toast.LENGTH_SHORT).show();
                    }
                }

                override fun onFailure(call: Call<APIresponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,t!!.message,Toast.LENGTH_SHORT).show();
                }

            })
    }

}