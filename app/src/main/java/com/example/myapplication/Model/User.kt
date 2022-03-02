package com.example.myapplication.Model

class User {

    var username:String=""
    get()=field
    set(value){
        field=value
    }

    var email:String=""
        get()=field
        set(value){
            field=value
        }

    var password:String=""
        get()=field
        set(value){
            field=value
        }

    constructor(){}

    constructor(username: String, email: String, password: String) {
        this.username = username
        this.email = email
        this.password = password
    }


}