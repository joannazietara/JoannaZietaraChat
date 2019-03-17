package com.joannazietara.chat.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joannazietara.chat.R
import com.joannazietara.chat.model.ChatMessage

class MessagesAdapter(private val messages: List<ChatMessage>): RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return messages[position].author
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = if(viewType == ChatMessage.USER)
            LayoutInflater.from(parent.context)
                .inflate(R.layout.message_user, parent, false)
        else
            LayoutInflater.from(parent.context)
                .inflate(R.layout.message_bot, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvMessage.text = messages[position].message
    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var tvMessage: TextView = view.findViewById(R.id.tvChatMessage)
}