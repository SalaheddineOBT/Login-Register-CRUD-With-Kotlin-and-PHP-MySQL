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