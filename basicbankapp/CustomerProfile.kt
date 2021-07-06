package com.example.basicbankapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_customer_profile.*
import kotlinx.android.synthetic.main.dialog_box.view.*

class CustomerProfile : AppCompatActivity() {
    lateinit var db:DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_profile)

        //back button
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        actionbar.title = "Customer Profile"


       val myname = intent.getStringExtra("NAME")
        name_got.text = myname

        val accountno = intent.getStringExtra("ACC_NO")
        acc_no_got.text = accountno

        val account_bal = intent.getIntExtra("ACC_BAL",1)
        acc_balance_got.text = account_bal.toString()

        val acc_email = intent.getStringExtra("EMAIL")
        email_got.text = acc_email

        val acc_phone = intent.getIntExtra("PHONE",0)
        phone_got.text = acc_phone.toString()



        transfer.setOnClickListener {
            showdialog()
        }

    }

    private fun showdialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_box,null)
        val editText = dialogLayout.amt_to_be_sent

        with(builder){
            setTitle("Amount")

            setPositiveButton("OK"){dialog,which->
                val store: Int = editText.text.toString().toInt()

                    if(store > acc_balance_got.text.toString().toInt()) {
                        Toast.makeText(
                            this@CustomerProfile,
                            "Insufficient Balance!",
                            Toast.LENGTH_SHORT).show()
                        editText.requestFocus()
                    }
                else if(store==0)
                    {Toast.makeText(
                        this@CustomerProfile,
                        "Amount can't be zero!",
                        Toast.LENGTH_SHORT).show()
                        editText.requestFocus()}
                else{
                         val intent = Intent(this@CustomerProfile,SendToUser::class.java)

                            intent.putExtra("FROM_ACC",acc_no_got.text)
                            intent.putExtra("AMOUNT",store.toString())

                        startActivity(intent)
                        }

                    }



            setNegativeButton("Cancel"){dialog,which->
                Toast.makeText(this@CustomerProfile,"Transaction Aborted!",Toast.LENGTH_SHORT).show()
            }
            setView(dialogLayout)
            show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.arrow,menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.midetails-> gotoTransaction()
            else->onSupportNavigateUp()
        }
        return true
    }

    fun gotoTransaction()
    {
        val intent = Intent(this,TransctionHistory::class.java)
        startActivity(intent)
    }


}