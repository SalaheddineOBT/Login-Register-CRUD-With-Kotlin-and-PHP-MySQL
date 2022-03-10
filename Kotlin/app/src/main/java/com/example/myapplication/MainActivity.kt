package com.example.myapplication

import android.app.blob.BlobStoreManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnlogout:AppCompatButton=findViewById(R.id.btnLogout)
        btnlogout.setVisibility(View.VISIBLE)
        val usertxt:TextView=findViewById(R.id.txtUser)
        val txttitl:TextView=findViewById(R.id.txttitl)

        val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val mGoogleSignInClient: GoogleSignInClient = GoogleSignIn.getClient(this@MainActivity, gso);

        val nom=intent.getStringExtra("UserName")

        val acct = GoogleSignIn.getLastSignedInAccount(this)

        if(nom != null) usertxt.text=nom;
        else if(acct!=null) usertxt.text=acct.displayName;
        else startActivity(Intent(this@MainActivity, LoginActivity::class.java))

        btnlogout.setOnClickListener(){
            mGoogleSignInClient.signOut().addOnCompleteListener{
                finish()
            }
            finish()
            usertxt.text=""
            txttitl.text=""
            btnlogout.setVisibility(View.INVISIBLE)
            LoginManager.getInstance().logOut();
        }

    }
}