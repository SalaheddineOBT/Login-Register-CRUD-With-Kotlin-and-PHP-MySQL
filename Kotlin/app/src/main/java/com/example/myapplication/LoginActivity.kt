package com.example.myapplication

import android.content.*
import android.os.Bundle
import android.text.method.*
import android.widget.*
import androidx.appcompat.app.*
import androidx.appcompat.widget.*
import com.android.volley.*
import com.android.volley.toolbox.*
import org.json.JSONObject

class LoginActivity : AppCompatActivity(){
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin:AppCompatButton=findViewById(R.id.btnLogin)
        val emailinput:EditText=findViewById(R.id.EmailInput)
        val passwordinput:EditText=findViewById(R.id.PasswordInput)
        val showHide:ImageView=findViewById(R.id.showhidepasswordbtn)
        val forgotPass:TextView=findViewById(R.id.forgotbtn)
        val googllbtn:RelativeLayout=findViewById(R.id.btnGoogle)
        val facebookbtn:RelativeLayout=findViewById(R.id.btnFacebook)
        val signupbtn:TextView=findViewById(R.id.txtSignup)

        var v=false

        //Show & Hide Password :
        showHide.setOnClickListener(){
            if(v != true){
                v=true
                showHide.setBackgroundResource(R.drawable.hide)
                passwordinput.transformationMethod=HideReturnsTransformationMethod.getInstance()
            }else
            {
                v=false
                showHide.setBackgroundResource(R.drawable.view)
                passwordinput.transformationMethod=PasswordTransformationMethod.getInstance()
            }
        }

        //Register Button :
        signupbtn.setOnClickListener{
            val intent=Intent(this@LoginActivity,RegisterActivity::class.java)
            startActivity(intent)
        }

        //Login Button :
        btnLogin.setOnClickListener{
            val email:String =emailinput.text.toString()
            val pass:String=passwordinput.text.toString()

            val url:String="http://10.0.2.2/API V2/Operations/Login.php"

            val params=HashMap<String,String>()
            params["email"]=email
            params["password"]=pass
            val jO=JSONObject(params as Map<*, *>)

            val rq:RequestQueue=Volley.newRequestQueue(this@LoginActivity)

            val jor=JsonObjectRequest(Request.Method.POST,url,jO,Response.Listener { res->
                try {
                    if(res.getString("success").equals("1")){
                        val intent=Intent(this@LoginActivity,MainActivity::class.java)
                        intent.putExtra("UserName",res.getString("user"))
                        startActivity(intent)
                        emailinput.text.clear()
                        passwordinput.text.clear()
                    } else { alert("Message d'Erreur !",res.getString("message")) }

                }catch (e:Exception){
                    alert("Message d'Erreur !",""+e.message)
                }
            },Response.ErrorListener { err->
                alert("Message d'Erreur !",""+err.message)
            })

            rq.add(jor)
        }
    }

    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@LoginActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }
}