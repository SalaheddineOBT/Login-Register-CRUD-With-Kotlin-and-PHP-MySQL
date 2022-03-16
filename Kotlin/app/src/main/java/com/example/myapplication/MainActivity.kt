package com.example.myapplication

import android.content.*
import androidx.appcompat.app.*
import android.os.Bundle
import android.view.View
import android.widget.*
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnlogout:RelativeLayout=findViewById(R.id.btnLogout)
        val btnallUsers:RelativeLayout=findViewById(R.id.btnallUsers)
        val btnNewUser:RelativeLayout=findViewById(R.id.btnNewUser)
        btnNewUser.setVisibility(View.VISIBLE)
        btnlogout.setVisibility(View.VISIBLE)
        btnallUsers.setVisibility(View.VISIBLE)
        val usertxt:TextView=findViewById(R.id.txtUser)
        val txttitl:TextView=findViewById(R.id.txttitl)

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this@MainActivity, gso);

        val nom=intent.getStringExtra("UserName")

        if(nom != null) usertxt.text=nom; else startActivity(Intent(this@MainActivity, LoginActivity::class.java))

        btnlogout.setOnClickListener(){
            mGoogleSignInClient.signOut().addOnCompleteListener{
                finish()
            }
            finish()
            usertxt.text=""
            txttitl.text=""
            btnlogout.setVisibility(View.INVISIBLE)
            btnallUsers.setVisibility(View.INVISIBLE)
            btnNewUser.setVisibility(View.INVISIBLE)
            LoginManager.getInstance().logOut();
        }
        btnNewUser.setOnClickListener{
            val intent=Intent(this@MainActivity,usermanager::class.java)
            intent.putExtra("Methode","Add User")
            startActivity(intent)
        }
        btnallUsers.setOnClickListener{startActivity(Intent(this@MainActivity,Listofusers::class.java))}
    }
}