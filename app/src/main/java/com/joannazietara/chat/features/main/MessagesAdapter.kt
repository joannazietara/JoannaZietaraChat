package com.joannazietara.chat.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joannazietara.chat.R
import com.joannazietara.chat.model.ChatMessage

class MessagesAdapter(private val listener: MessageAdapterListener, private val messages: List<ChatMessage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return messages[position].author
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == ChatMessage.USER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.message_user, parent, false)
            UserMessageViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.message_bot, parent, false)
            BotMessageViewHolder(listener, view)
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == ChatMessage.USER) {
            val viewHolder: UserMessageViewHolder = holder as UserMessageViewHolder
            viewHolder.tvMessage.text = messages[position].message
        } else {
            val viewHolder: BotMessageViewHolder = holder as BotMessageViewHolder
            if (messages[position].message.contains("/drawable")) {
                viewHolder.ivChatImage.visibility = View.VISIBLE
                viewHolder.tvMessage.visibility = View.GONE
            } else {
                viewHolder.ivChatImage.visibility = View.GONE
                viewHolder.tvMessage.visibility = View.VISIBLE
                holder.tvMessage.text = messages[position].message
            }
        }
    }
}

class UserMessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
    var tvMessage: TextView = view.findViewById(R.id.tvChatMessage)
}

class BotMessageViewHolder(private val listener: MessageAdapterListener, view: View): RecyclerView.ViewHolder(view) {
    var tvMessage: TextView = view.findViewById(R.id.tvChatMessage)
    var ivChatImage: ImageView = view.findViewById(R.id.ivChatImage)

    init {
        ivChatImage.setOnClickListener { listener.onImageClicked() }
    }
}

interface MessageAdapterListener {
    fun onImageClicked()
}