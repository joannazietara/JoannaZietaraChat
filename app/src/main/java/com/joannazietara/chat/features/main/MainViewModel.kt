package com.joannazietara.chat.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joannazietara.chat.R
import com.joannazietara.chat.model.ChatMessage
import java.util.*

class MainViewModel: ViewModel() {
    val roomsMessages: MutableLiveData<List<ChatMessage>> by lazy {
        MutableLiveData<List<ChatMessage>>()
    }

    fun addUserMessage(text: String, categoryId: Int) {
        when (categoryId) {
            R.id.navigation_rooms -> {
                roomsMessages.add(ChatMessage(text, ChatMessage.USER))
            }
            else -> {
            }
        }

    }

    fun <T> MutableLiveData<List<T>>.add(item: T) {
        val updatedItems = this.value as ArrayList
        updatedItems.add(item)
        this.value = updatedItems
    }
}