package com.example.myapplication

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.*
import androidx.appcompat.app.AlertDialog

class RegisterActivity : AppCompatActivity(),IVolley {

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
        //val btnfb=findViewById<RelativeLayout>(R.id.btnFb)
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
            val username=usernametxt.text.toString();
            val email=emailtxt.text.toString().trim();
            val password=passwordtxt.text.toString().trim()
            val confirm=confirmtxt.text.toString().trim()
            if(password == confirm){
                MyVolleyRequest.getInstance(this@RegisterActivity,this@RegisterActivity)
                    .postRequest("http://172.16.1.47/API%20PHP/Operations/Register.php",username,email,confirm)
                usernametxt.text.clear()
                emailtxt.text.clear()
                confirmtxt.text.clear()
                passwordtxt.text.clear()
            }else{
                Alert("Message Error : ","Confirm Password is Incorrect !");
            }
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
            val builder= AlertDialog.Builder(this@RegisterActivity)
            builder.setTitle("Message Information :")
            builder.setMessage("")
            builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int ->

            }).create()
            builder.show()
        }
    }

    fun Alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@RegisterActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}