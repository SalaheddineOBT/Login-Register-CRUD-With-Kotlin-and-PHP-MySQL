package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isInvisible

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnlogout=findViewById<AppCompatButton>(R.id.btnLogout)

        btnlogout.setVisibility(View.VISIBLE);

        val usertxt=findViewById<TextView>(R.id.txtUser)
        val txttitl=findViewById<TextView>(R.id.txttitl);

        val nom=intent.getStringExtra("UserName");

        usertxt.text=nom;

        btnlogout.setOnClickListener(){
            finish();
            usertxt.text=""
            txttitl.text=""
            btnlogout.setVisibility(View.INVISIBLE);
        }

    }
}