package com.joannazietara.chat.model

/**
 * Reprezentuje wiadomość na czacie.
 *
 * @property author     Autor wiadomosci, czy jest to użytkownik ([USER]) czy chat-bot ([BOT])
 * @property message    Treść wiadomości
 * @constructor Tworzy obiekt wiadomosci
 */
class ChatMessage(val message: String, val author: Int) {
    companion object {
        const val USER = 1
        const val BOT = 2
    }
}