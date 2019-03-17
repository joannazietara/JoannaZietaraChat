package com.joannazietara.chat.model

import com.joannazietara.chat.R
import java.util.*

class QuestionResolver {
    fun getAnswer(categoryId: Int, question: String): Int {
        var splitedQuestion = unifyText(question).split(" ")
        for (key: Int in RoomsAnswers.keywords.keys) {
            for(keywords: List<String> in RoomsAnswers.keywords[key]!!) {
                if(splitedQuestion.containsAll(keywords))
                    return RoomsAnswers.answers[key]!!
            }
        }

        return R.array.no_answer
    }

    private fun unifyText(text: String): String {
        var text = text.toLowerCase()
        text = replaceSpecialCharacters(text)
        text = text.replace(" +".toRegex(), " ").trim { it <= ' ' }

        return text
    }

    private fun replaceSpecialCharacters(text: String): String {
        val sbText = StringBuilder(text)
        val len = text.length

        for (i in 0 until len) {
            val character = sbText[i]

            if (characters.containsKey(character))
                sbText.setCharAt(i, characters[character]!!)
        }

        return sbText.toString()
    }

    val characters = hashMapOf<Char, Char>(
        'ą' to 'a', 'ę' to 'e', 'ś' to 's', 'ó' to 'o', 'ł' to 'l', 'ź' to 'z', 'ż' to 'z', 'ć' to 'c', 'ń' to 'n',
        '.' to ' ', ',' to ' ', '?' to ' ', '"' to ' ', '(' to ' ', ')' to ' ', '/' to ' ', '\\' to ' ', '\'' to ' ',
        ':' to ' ', ';' to ' ', '-' to ' ', '\ufeff' to ' ', '\ufffe' to ' ', '!' to ' ', '@' to ' ', '#' to ' ',
        '$' to ' ', '%' to ' ', '^' to ' ', '*' to ' ', '_' to ' ', '+' to ' ', '=' to ' ', '{' to ' ', '}' to ' ',
        '[' to ' ', ']' to ' ', '|' to ' ', '<' to ' ', '>' to ' ', '1' to ' ', '2' to ' ', '3' to ' ', '4' to ' ',
        '5' to ' ', '6' to ' ', '7' to ' ', '8' to ' ', '9' to ' ', '0' to ' ')
}