package com.joannazietara.chat.model

import com.joannazietara.chat.R
import com.joannazietara.chat.model.answers.AnswersCategory
import com.joannazietara.chat.model.answers.OtherAnswersCategory
import com.joannazietara.chat.model.answers.RoomsAnswersCategory
import com.joannazietara.chat.model.answers.WhereAnswersCategory

class QuestionResolver {
    fun getAnswer(categoryId: Int, question: String): Int {
        val questionWords = unifyText(question).split(" ")
        val answersCat = getProperCategory(categoryId)
        for (key: Int in answersCat.getKeywords().keys) {
            for (keywords: List<String> in answersCat.getKeywords()[key]!!) {
                if (questionWords.containsAll(keywords))
                    return answersCat.getAnswers()[key]!!
            }
        }

        return R.array.no_answer
    }

    private fun getProperCategory(categoryId: Int): AnswersCategory {
        return when (categoryId) {
            R.id.navigation_rooms -> RoomsAnswersCategory()
            R.id.navigation_where -> WhereAnswersCategory()
            else -> OtherAnswersCategory()
        }
    }

    private fun unifyText(text: String): String {
        var question = text.toLowerCase()
        question = replaceSpecialCharacters(question)
        question = question.replace(" +".toRegex(), " ").trim { it <= ' ' }

        return question
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

    private val characters = hashMapOf(
        'ą' to 'a', 'ę' to 'e', 'ś' to 's', 'ó' to 'o', 'ł' to 'l', 'ź' to 'z', 'ż' to 'z', 'ć' to 'c', 'ń' to 'n',
        '.' to ' ', ',' to ' ', '?' to ' ', '"' to ' ', '(' to ' ', ')' to ' ', '/' to ' ', '\\' to ' ', '\'' to ' ',
        ':' to ' ', ';' to ' ', '-' to ' ', '\ufeff' to ' ', '\ufffe' to ' ', '!' to ' ', '@' to ' ', '#' to ' ',
        '$' to ' ', '%' to ' ', '^' to ' ', '*' to ' ', '_' to ' ', '+' to ' ', '=' to ' ', '{' to ' ', '}' to ' ',
        '[' to ' ', ']' to ' ', '|' to ' ', '<' to ' ', '>' to ' ', '1' to ' ', '2' to ' ', '3' to ' ', '4' to ' ',
        '5' to ' ', '6' to ' ', '7' to ' ', '8' to ' ', '9' to ' ', '0' to ' '
    )
}