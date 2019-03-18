package com.joannazietara.chat.model.answers

import com.joannazietara.chat.R

class OtherAnswersCategory : AnswersCategory {
    override fun getAnswers(): HashMap<Int, Int> {
        return answers
    }

    override fun getKeywords(): HashMap<Int, List<List<String>>> {
        return keywords
    }

    companion object {
        private val keywords = hashMapOf(
            0 to listOf(listOf("parkowac"), listOf("parking")),
            1 to listOf(listOf("otwarta", "uczelnia"), listOf("otwarcia", "uczelni")),
            2 to listOf(listOf("wynajecia", "sali"), listOf("wynajecie", "sali")),
            3 to listOf(
                listOf("uczyc", "przerwie"),
                listOf("pouczyc", "przerwie"),
                listOf("coworking"),
                listOf("coworkingu")
            ),
            4 to listOf(listOf("pytanie", "inne"))
        )
        private val answers = hashMapOf(
            0 to R.array.other_answer_0,
            1 to R.array.other_answer_1,
            2 to R.array.other_answer_2,
            3 to R.array.other_answer_3,
            4 to R.array.other_answer_4
        )
    }
}