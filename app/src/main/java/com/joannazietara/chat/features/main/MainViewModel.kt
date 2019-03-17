package com.joannazietara.chat.features.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joannazietara.chat.R
import com.joannazietara.chat.model.ChatMessage
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainViewModel: ViewModel() {
    val messages:      MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>> by lazy { MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>>() }
//    val roomsMessages: MutableLiveData<List<ChatMessage>> by lazy { MutableLiveData<List<ChatMessage>>() }
//    val whereMessages: MutableLiveData<List<ChatMessage>> by lazy { MutableLiveData<List<ChatMessage>>() }
//    val otherMessages: MutableLiveData<List<ChatMessage>> by lazy { MutableLiveData<List<ChatMessage>>() }

    init {
        messages.value = HashMap()
    }

    fun addUserMessage(text: String, categoryId: Int) {
        messages.add(categoryId, ChatMessage(text, ChatMessage.USER))
        messages.add(categoryId, ChatMessage(text + text, ChatMessage.BOT))
    }

    fun HashMap<Int, ArrayList<ChatMessage>>.getByKey(id: Int): ArrayList<ChatMessage> {
        return ArrayList()
    }

    fun MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>>.add(id: Int, item: ChatMessage) {
        val updatedItems = this.value as HashMap

        if(!updatedItems.containsKey(id))
            updatedItems[id] = ArrayList()
        updatedItems[id]!!.add(item)

        this.value = updatedItems
    }
}