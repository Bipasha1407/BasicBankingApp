package com.example.basicbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.basicbankapp.mypkg.getCurrentDateTime
import com.example.basicbankapp.mypkg.toString
import kotlinx.android.synthetic.main.activity_customer_list.*

class SendToUser : AppCompatActivity(),OnClickListener {
    private lateinit var db:DBHelper
    private var listperson:List<ExampleItem> = ArrayList<ExampleItem>()

    private var layoutManager: RecyclerView.LayoutManager?=null
    private var myadapter: RecyclerView.Adapter<SendToAdapter.CustomerHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_list)

        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.title = "Select Customer"

        db= DBHelper(this)
        refreshdata()

    }


    override fun onitemClicked(position: Int) {

        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")

        val i = Intent(this@SendToUser,TransctionHistory::class.java)
        val person = SentAmount()
        person.from = intent.getStringExtra("FROM_ACC")
        person.to = listperson[position].Acc_No
        person.amt = intent.getStringExtra("AMOUNT")
        person.status = "Success"
        person.date = dateInString

        TransctionHistory.db.addperson(person)

        Toast.makeText(this,"Transaction Successful!",Toast.LENGTH_SHORT).show()
       startActivity(i)
    }

    private fun refreshdata()
    {
        listperson = db.allperson
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        myadapter = SendToAdapter(listperson,this)
        recycler_view.adapter = myadapter
        (myadapter as SendToAdapter).notifyDataSetChanged()
    }
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

}

