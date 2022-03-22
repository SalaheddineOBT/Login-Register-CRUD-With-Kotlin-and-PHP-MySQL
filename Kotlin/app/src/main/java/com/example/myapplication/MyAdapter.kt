package com.example.myapplication

import android.content.*
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MyAdapter (var ctx:Context, var ressource:Int, var items:ArrayList<User>) : ArrayAdapter<User>(ctx,ressource,items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater= LayoutInflater.from(ctx)

        val view:View=layoutInflater.inflate(ressource,null)

        val txtemail:TextView=view.findViewById(R.id.textEmail)
        val txtusername:TextView=view.findViewById(R.id.textUserName)
        val deletebtn:ImageButton=view.findViewById(R.id.deletbtn)
        val updatebtn:LinearLayout=view.findViewById(R.id.inte)

        var user:User=items[position]

        txtemail.text=user.email
        txtusername.text=user.username

        deletebtn.setOnClickListener{
            val builder= AlertDialog.Builder(context)
            builder.setTitle("Message de Confirmation :")
            builder.setMessage("Are You Sure You Want To Delete This User ?")
            builder.setPositiveButton("Yes",{ dialogInterface: DialogInterface, i: Int -> deleteUser(user.id) })
            .setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> }).create()
            builder.show()
        }

        updatebtn.setOnClickListener{
            var intent= Intent(ctx,updateUser::class.java)
            intent.putExtra("id",user.id)
            ctx.startActivity(intent);
        }

        return view
    }

    fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(ctx)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }

    fun deleteUser(id:Int){
        val url="http://172.16.1.47/API%20PHP/Operations/CRUD/Delete.php"
        val params=HashMap<String,String>()
        params["id"]= ""+id
        val jO= JSONObject(params as Map<*, *>)
        val rq: RequestQueue = Volley.newRequestQueue(ctx)
        val jor= JsonObjectRequest(Request.Method.DELETE,url,jO, Response.Listener { res->
            try {
                if(res.getString("success").equals("1")){
                    alert("Message D'information :",res.getString("message"))
                } else { alert("Message d'Erreur !",res.getString("message")) }
            }catch (e:Exception){
                alert("Message d'Erreur !",""+e.message)
            }
        }, Response.ErrorListener { err->
            alert("Message d'Erreur !",""+err.message)
        })
        rq.add(jor)
    }

}