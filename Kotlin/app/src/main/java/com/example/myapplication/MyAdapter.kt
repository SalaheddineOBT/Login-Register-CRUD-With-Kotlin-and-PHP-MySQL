package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class MyAdapter (var ctx:Context, var ressource:Int, var items:ArrayList<User>) : ArrayAdapter<User>(ctx,ressource,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater= LayoutInflater.from(ctx)

        val view:View=layoutInflater.inflate(ressource,null)

        val txtemail:TextView=view.findViewById(R.id.textEmail)
        val txtusername:TextView=view.findViewById(R.id.textUserName)

        var user:User=items[position]

        txtemail.text=user.email
        txtusername.text=user.username

        return view
    }
}