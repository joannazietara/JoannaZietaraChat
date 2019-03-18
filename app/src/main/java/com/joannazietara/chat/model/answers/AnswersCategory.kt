package com.joannazietara.chat.model.answers

interface AnswersCategory {
    fun getAnswers(): HashMap<Int, Int>
    fun getKeywords(): HashMap<Int, List<List<String>>>
}