package com.example.basicbankapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.transaction_view.view.*

class TransactionAdapter(val sentList:List<SentAmount>):
    RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.TransactionHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.transaction_view,parent,
            false)

        return TransactionHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.TransactionHolder, position: Int) {
        val current = sentList[position]

        holder.acc_from.text = current.from
        holder.acc_to.text = current.to
        holder.status.text = current.status
        holder.sent_amt.text = current.amt.toString()
        holder.date_time.text = current.date

    }

    override fun getItemCount(): Int {
        return sentList.size
    }

        inner class TransactionHolder(item: View): RecyclerView.ViewHolder(item){

            val acc_from:TextView = item.from_acc
            val acc_to:TextView = item.to_acc
            val status:TextView = item.result
            val sent_amt:TextView = item.amt
            val date_time:TextView = item.transfer_time

        }
}