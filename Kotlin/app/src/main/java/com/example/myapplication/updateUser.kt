package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class updateUser : AppCompatActivity() {

    lateinit var imageuser:ImageView
    lateinit var username:EditText
    lateinit var email:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        val btn:AppCompatButton=findViewById(R.id.btnsave)
        imageuser=findViewById(R.id.pictureofuser)
        username=findViewById(R.id.editeUsername)
        email=findViewById(R.id.editeEmail)

        val back:ImageView=findViewById(R.id.back)
        back.setOnClickListener{ finish() }

        val id=intent.getIntExtra("id",0)
        if(id.equals(null))startActivity(Intent(this, Listofusers::class.java)) else Afficher(id)

        btn.setOnClickListener{
            if(checkInternet()) Save(id,username.text.toString(),email.text.toString())
            else alert("Erreur :","No Network Connected !")
        }

    }

    private fun Afficher(id : Int){
        val url="http://172.16.1.47/API%20PHP/Operations/CRUD/Read.php?id="+id
        val params=HashMap<String,String>()
        params["select"]="ById"
        val jO= JSONObject(params as Map<*, *>)
        val rq: RequestQueue = Volley.newRequestQueue(this)
        val jor= JsonObjectRequest(Request.Method.POST,url,jO, Response.Listener { res->
            try {
                if(res.getString("success").equals("1")){
                    var data=res.getJSONObject("User")
                    username.setText(data.getString("UserName").toString())
                    email.setText(data.getString("Email").toString())
                } else { alert("Message d'Erreur !",res.getString("message")) }
            }catch (e:Exception){
                alert("Message d'Erreur !",""+e.message)
            }
        }, Response.ErrorListener { err->
            alert("Message d'Erreur !",""+err.message)
        })
        rq.add(jor)
    }

    private fun Save(id:Int,username:String,email:String){
        val url="http://172.16.1.47/API%20PHP/Operations/CRUD/Update.php"
        val params=HashMap<String,String>()
        params["id"]= ""+id
        params["username"]=username
        params["email"]=email
        val jO= JSONObject(params as Map<*, *>)
        val rq: RequestQueue = Volley.newRequestQueue(this)
        val jor= JsonObjectRequest(Request.Method.PATCH,url,jO, Response.Listener { res->
            try {
                if(res.getString("success").equals("1")){
                    val builder= AlertDialog.Builder(this)
                    builder.setTitle("Message d'Erreur !")
                    builder.setMessage(res.getString("message"))
                    builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int ->
                        finish()
                    }).create()
                    builder.show()
                } else { alert("Message d'Erreur !",res.getString("message")) }
            }catch (e:Exception){
                alert("Message d'Erreur !",""+e.message)
            }
        }, Response.ErrorListener { err->
            alert("Message d'Erreur !",""+err.message)
        })
        rq.add(jor)
    }

    private fun checkInternet():Boolean{
        val connManager: ConnectivityManager =this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiConn=connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobilData=connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if(!wifiConn!!.isConnectedOrConnecting && !mobilData!!.isConnectedOrConnecting)
            return false
        else
            return true
    }

    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }

}