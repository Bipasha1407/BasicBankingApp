package com.example.basicbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_customer_list.*

class CustomerList : AppCompatActivity(),OnItemClickListener {
     private lateinit var db:DBHelper
    private var listperson:List<ExampleItem> = ArrayList()

    private var layoutManager:RecyclerView.LayoutManager?=null
    private var myadapter:RecyclerView.Adapter<CustomerAdapter.CustomerHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.title="Customer List"

        db = DBHelper(this)
        refreshdata()

    }


    override fun onitemClicked(position: Int) {
        val intent = Intent(this,CustomerProfile::class.java)
        intent.putExtra("NAME",listperson[position].Name)
        intent.putExtra("ACC_NO",listperson[position].Acc_No)
        intent.putExtra("ACC_BAL",listperson[position].Acc_Bal)
        intent.putExtra("EMAIL",listperson[position].Email)
        intent.putExtra("PHONE",listperson[position].Phone)

        startActivity(intent)
    }

    private fun refreshdata()
    {
        listperson = db.allperson

       layoutManager = LinearLayoutManager(this)

        recycler_view.layoutManager =layoutManager

        myadapter = CustomerAdapter(listperson,this)
        recycler_view.adapter = myadapter
        (myadapter as CustomerAdapter).notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

}