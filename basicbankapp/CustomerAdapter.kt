package com.example.basicbankapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cust_list_view.view.*

class CustomerAdapter(val exampleList: List<ExampleItem>,
                      private val onItemClickListener: OnItemClickListener
): RecyclerView.Adapter<CustomerAdapter.CustomerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cust_list_view,parent,
            false)

        return CustomerHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomerHolder, position: Int) {
        val current = exampleList[position]

        holder.itemName.text = current.Name
        holder.itemPhone.text = current.Phone.toString()
        //holder.itembalance.text = current.Acc_Bal.toString()

        holder.itemView.setOnClickListener {
            onItemClickListener.onitemClicked(position)
        }

    }
    override fun getItemCount(): Int {
        return exampleList.size
    }

    inner class CustomerHolder(item: View): RecyclerView.ViewHolder(item){

        val itemName: TextView = item.tvname
        val itemPhone: TextView = item.tvphone
       // val itembalance:TextView = item.bankbalance

    }
}

interface OnItemClickListener {
    fun onitemClicked(position: Int)

}
