package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.databinding.ActivityMainBinding
import org.json.JSONObject

class Listofusers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listofusers)

        val lst:ListView=findViewById(R.id.lstUsers)

        if(checkInternet()){
            val url="http://172.16.1.47/API%20PHP/Operations/CRUD/Read.php"
            val params=HashMap<String,String>()
            params["select"]="All"
            val jO= JSONObject(params as Map<*, *>)
            val rq: RequestQueue = Volley.newRequestQueue(this@Listofusers)
            val jor= JsonObjectRequest(Request.Method.POST,url,jO, Response.Listener { res->
                try {
                    if(res.getString("success").equals("1")){
                        val dt=res.getJSONArray("Users")
                        //alert("Hello",""+dt.getJSONObject(0).getString("UserName"))
                        for(i in 0 until dt.length()){
                            //val usr=User(dt.getJSONObject(i).getString("UserName"),dt.getJSONObject(i).getString("Email"))

                            //alert("Hello",""+dt.getJSONObject(i).getString("UserName"))
                        }
                    } else { alert("Message d'Erreur !",res.getString("message")) }

                }catch (e:Exception){
                    alert("Message d'Erreur !",""+e.message)
                }
            }, Response.ErrorListener { err->
                alert("Message d'Erreur !",""+err.message)
            })
            rq.add(jor)
        }else
            alert("Erreur :","No Network Connected !")
    }

    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@Listofusers)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
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

}