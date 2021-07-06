package com.example.basicbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_homescreen.*

class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        to_customer_list.setOnClickListener {

            val intent = Intent(this,CustomerList::class.java)
            startActivity(intent)
        }

        to_transaction.setOnClickListener {
            val intent = Intent(this,TransctionHistory::class.java)
            startActivity(intent)
        }


    }
}