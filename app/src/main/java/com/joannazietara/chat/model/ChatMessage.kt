package com.joannazietara.chat.model

class ChatMessage(val message: String, val author: Int) {
    companion object {
        val USER = 1
        val BOT = 2
    }
}