package com.joannazietara.chat.model

class ChatMessage(val message: String, val author: Int) {
    companion object {
        const val USER = 1
        const val BOT = 2
    }
}