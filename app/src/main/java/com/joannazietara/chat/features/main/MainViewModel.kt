package com.joannazietara.chat.features.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.joannazietara.chat.model.ChatMessage
import com.joannazietara.chat.model.QuestionResolver
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val messages: MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>> by lazy { MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>>() }
    private val questionResolver = QuestionResolver()
    private val random = Random()

    init {
        messages.value = HashMap()
    }

    fun addUserMessage(text: String, categoryId: Int) {
        messages.add(categoryId, ChatMessage(text, ChatMessage.USER))
        messages.add(categoryId, ChatMessage(getAnswer(categoryId, text), ChatMessage.BOT))
    }

    private fun getAnswer(categoryId: Int, question: String): String {
        val answersId = questionResolver.getAnswer(categoryId, question)
        val answers = getApplication<Application>().resources.getStringArray(answersId)
        val index = random.nextInt(answers.size)

        return answers[index]
    }

    private fun MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>>.add(id: Int, item: ChatMessage) {
        val updatedItems = this.value as HashMap

        if (!updatedItems.containsKey(id))
            updatedItems[id] = ArrayList()
        updatedItems[id]!!.add(item)

        this.value = updatedItems
    }
}