package com.example.myapplication.Model

class APIresponse {

    var success: Int =0
    get() = field
    set(value) {
        field=value
    }

    var status: Int =0
    get() = field
    set(value) {
        field=value
    }

    var token:String?=null
    get() = field
    set(value) {
        field=value
    }

    var message:String?=null
    get() = field
    set(value) {
        field=value
    }

    var user:User?=null
    get() = field
    set(value) {
        field=value
    }

}