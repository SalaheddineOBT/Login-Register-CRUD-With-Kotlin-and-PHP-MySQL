package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

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

    }
}