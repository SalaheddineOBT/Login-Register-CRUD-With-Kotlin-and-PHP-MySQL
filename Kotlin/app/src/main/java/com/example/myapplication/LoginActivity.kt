package com.example.myapplication

import android.content.*
import android.os.Bundle
import android.text.method.*
import android.widget.*
import androidx.appcompat.app.*
import androidx.appcompat.widget.*
import com.android.volley.*
import com.android.volley.toolbox.*
import com.google.android.gms.auth.api.signin.*
import org.json.JSONObject
import androidx.core.app.ActivityCompat.*
import android.content.Intent
import android.net.ConnectivityManager
import androidx.core.app.*
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity(){

    lateinit var callbackManager: CallbackManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin:AppCompatButton=findViewById(R.id.btnLogin)
        val emailinput:EditText=findViewById(R.id.EmailInput)
        val passwordinput:EditText=findViewById(R.id.PasswordInput)
        val showHide:ImageView=findViewById(R.id.showhidepasswordbtn)
        val forgotPass:TextView=findViewById(R.id.forgotbtn)
        val googllbtn: RelativeLayout =findViewById(R.id.sign_in_button)
        val facebookbtn: LoginButton =findViewById(R.id.btnFacebook)
        val signupbtn:TextView=findViewById(R.id.txtSignup)

        var v=false

        //Show & Hide Password :
        showHide.setOnClickListener(){
            if(v != true){
                v=true
                showHide.setBackgroundResource(R.drawable.hide)
                passwordinput.transformationMethod=HideReturnsTransformationMethod.getInstance()
            } else {
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
            if(checkInternet()){
                val email:String =emailinput.text.toString()
                val pass:String=passwordinput.text.toString()
                val url="http://172.16.1.47/API%20PHP/Operations/Login.php"
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
            }else
                alert("Erreur :","No Network Connected !")
        }

        //SignIn With Gmail :
        googllbtn.setOnClickListener{
            if(checkInternet()){
                val gso:GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().requestProfile().build()
                val mGoogleSignInClient:GoogleSignInClient = GoogleSignIn.getClient(this@LoginActivity, gso)
                val signInIntent = mGoogleSignInClient.signInIntent
                startActivityForResult(signInIntent, 1000)
            }else
                alert("Erreur :","No Network Connected !")
        }

        //SignIn With Facebook :
        facebookbtn.setOnClickListener{
            if(checkInternet()){
                //facebookbtn.setReadPermissions("email")
                callbackManager = CallbackManager.Factory.create()
                LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        val graphRequest =
                            GraphRequest.newMeRequest(result?.accessToken) { obj, response ->
                                try {
                                    if (obj.has("id")) {
                                        /*Log.d("FACEBOOKDATA",obj.getString("name"))
                                          Log.d("FACEBOOKDATA",obj.getString("email"))*/
                                        //Log.d("FACEBOOKDATA",JSONObject(obj.getString("picture")).getJSONObject("data").getString("url"))
                                        val intent =
                                            Intent(this@LoginActivity, MainActivity::class.java)
                                        intent.putExtra("UserName", "" + obj.getString("name"))
                                        startActivity(intent)
                                    }
                                } catch (e: Exception) {
                                    alert("Error !!!", "" + e.message)
                                }
                            }
                        val param = Bundle()
                        param.putString("fields", "name,email,id,picture.type(large)")
                        graphRequest.parameters = param
                        graphRequest.executeAsync()
                    }
                    override fun onCancel() {}
                    override fun onError(error: FacebookException?) {}
                })
            }else
                alert("Erreur :","No Network Connected !")
        }
    }

    private fun alert(title:String,message:String){
        val builder= AlertDialog.Builder(this@LoginActivity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Ok",{ dialogInterface: DialogInterface, i: Int -> }).create()
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode,resultCode,data)
        if (requestCode == 1000) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account: GoogleSignInAccount =task.getResult(ApiException::class.java)
                val intent=Intent(this@LoginActivity,MainActivity::class.java)
                intent.putExtra("UserName",account.displayName)
                startActivity(intent)
                //alert("message :",""+account.displayName)
            }catch (e:ApiException){
                alert("Message Erreur :",""+e.message)
            }
        }
    }

    private fun checkInternet():Boolean{
        val connManager:ConnectivityManager=this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifiConn=connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobilData=connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if(!wifiConn!!.isConnectedOrConnecting && !mobilData!!.isConnectedOrConnecting)
            return false
        else
            return true
    }

}