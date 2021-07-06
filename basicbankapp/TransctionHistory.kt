package com.example.basicbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_customer_list.*
import kotlinx.android.synthetic.main.activity_customer_list.recycler_view
import kotlinx.android.synthetic.main.activity_transction_history.*

class TransctionHistory : AppCompatActivity() {
    companion object{    lateinit var db: DBHelper
}
     private var layoutManager: RecyclerView.LayoutManager?=null
    private var myadapter: RecyclerView.Adapter<TransactionAdapter.TransactionHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transction_history)

        //back button
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.title="Transaction History"

        db = DBHelper(this)
            refresh()
    }

    private fun refresh() {
       val listener = db.getList(this)
        layoutManager = LinearLayoutManager(this)
        r_v.layoutManager = layoutManager

        myadapter = TransactionAdapter(listener)
        r_v.adapter = myadapter
      //  (myadapter as TransactionAdapter).notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.home_button,menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.midetails-> gotoHome()
            else->onSupportNavigateUp()
        }
        return true
    }

    private fun gotoHome() {
        val intent = Intent(this,HomeScreen::class.java)
        startActivity(intent)
    }

}