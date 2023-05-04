package com.example.chatapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter (val context : Context, val messageList : ArrayList<Message> ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val ITEM_RECIVE =1;
    val ITEM_SEND =2;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1){
            //inflate recieve
            val view :View = LayoutInflater.from(context).inflate(R.layout.recieve,parent,false)
            return RecieveViewHolder(view)
        }else{
            //inflate sent
            val view :View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return SentViewHolder(view)
        }

    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messageList[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.sendId)){
            return ITEM_SEND
        }
        return ITEM_RECIVE
    }

    override fun getItemCount(): Int {
        return messageList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val currentMessage  = messageList[position]
         if(holder.javaClass == SentViewHolder::class.java){
            //do the stuff for sent view holder


             val viewHolder = holder as SentViewHolder
             holder.sentMessage.text = currentMessage.message
         }else{
            // do the stuff for recieve view holder
             val viewHolder = holder as RecieveViewHolder
             holder.recieveMessage.text = currentMessage.message
         }
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val  sentMessage = itemView.findViewById<TextView>(R.id.text_message_send)
    }

    class RecieveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recieveMessage = itemView.findViewById<TextView>(R.id.text_message_recieve)

    }
}