package com.example.basicbankapp

class ExampleItem{

    var Name: String? = null
     var Acc_No: String?=null
    var Acc_Bal:Int = 1
    var Email :String? = null
    var Phone: Int = 0

    constructor(){}

    constructor(name:String,acc_no:String,acc_bal:Int,email:String,phone:Int)
    {
        Name = name
        Acc_No = acc_no
        Acc_Bal = acc_bal
        Email = email
        Phone = phone

    }

}