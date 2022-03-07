package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnlogout:AppCompatButton=findViewById(R.id.btnLogout)
        btnlogout.setVisibility(View.VISIBLE)
        val usertxt:TextView=findViewById(R.id.txtUser)
        val txttitl:TextView=findViewById(R.id.txttitl)

        val nom=intent.getStringExtra("UserName")
        if(nom != null) usertxt.text=nom; else startActivity(Intent(this@MainActivity, LoginActivity::class.java))

        btnlogout.setOnClickListener(){
            finish()
            usertxt.text=""
            txttitl.text=""
            btnlogout.setVisibility(View.INVISIBLE)
        }
    }
}