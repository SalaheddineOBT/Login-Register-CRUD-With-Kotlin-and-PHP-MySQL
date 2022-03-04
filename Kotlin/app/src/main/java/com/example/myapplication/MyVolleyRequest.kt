package com.example.myapplication

import android.content.Context
import android.graphics.Bitmap
import android.util.LruCache
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MyVolleyRequest {
    private var mRequestQuerue:RequestQueue?=null
    private var contex:Context?=null
    private var iVolley:IVolley?=null
    var imageLoader:ImageLoader?=null
        get

    val requestQueue:RequestQueue
    get() {
        if(mRequestQuerue == null)
            mRequestQuerue=Volley.newRequestQueue(contex!!.applicationContext,)

        return  mRequestQuerue!!
    }

    private constructor(context: Context,iVolley: IVolley){

        this.contex=context
        this.iVolley=iVolley
        mRequestQuerue=requestQueue

        this.imageLoader=ImageLoader(mRequestQuerue,object:ImageLoader.ImageCache {

            private val mCash=LruCache<String,Bitmap>(10)

            override fun getBitmap(url: String?): Bitmap {
                return mCash.get(url)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCash.put(url,bitmap)
            }

        })

    }

    private constructor(context: Context){

        this.contex=context

        mRequestQuerue=requestQueue

        this.imageLoader=ImageLoader(mRequestQuerue,object:ImageLoader.ImageCache {

            private val mCash=LruCache<String,Bitmap>(10)

            override fun getBitmap(url: String?): Bitmap {
                return mCash.get(url)
            }

            override fun putBitmap(url: String?, bitmap: Bitmap?) {
                mCash.put(url,bitmap)
            }

        })

    }

    fun <T> addToRequestQueue(req:Request<T>){
        requestQueue.add(req)
    }

    //Post Methode :
    fun postRequest(url:String,username:String,email:String,password:String){
        val postRequest=object:StringRequest(Request.Method.POST,url,
            Response.Listener { res->
                iVolley!!.onResponse(res.toString())
            }, Response.ErrorListener { e-> iVolley!!.onResponse(e.message!!) })
        {
            //CTRL + O
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()
                params["username"]="username"
                params["email"]=email
                params["password"]=password

                return params
            }
        }
        addToRequestQueue(postRequest)
    }

    companion object{
        private var mInstance:MyVolleyRequest?=null

        @Synchronized
        fun getInstance(context: Context):MyVolleyRequest{
            if(mInstance == null){
                mInstance= MyVolleyRequest(context)
            }
            return  mInstance!!
        }

        @Synchronized
        fun getInstance(context: Context, iVolley: IVolley):MyVolleyRequest{
            if(mInstance == null){
                mInstance=MyVolleyRequest(context,iVolley)
            }
            return  mInstance!!
        }

    }

}