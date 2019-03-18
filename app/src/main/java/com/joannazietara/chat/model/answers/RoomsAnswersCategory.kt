package com.joannazietara.chat.model.answers

import com.joannazietara.chat.R

class RoomsAnswersCategory: AnswersCategory {
    override fun getAnswers(): HashMap<Int, Int> {
        return answers
    }

    override fun getKeywords(): HashMap<Int, List<List<String>>> {
        return keywords
    }


    companion object {
        private val keywords = hashMapOf<Int, List<List<String>>>(0 to listOf(listOf("gdzie", "dziekanat"), listOf("jakim", "miejscu", "dziekanat"), listOf("jakim", "budynku", "dziekanat")),
            1 to listOf(listOf("kiedy", "dziekanat"), listOf("jakich", "godzinach", "dziekanat"), listOf("jakie", "dni", "dziekanat"), listOf("godziny", "dziekanat")),
            2 to listOf(listOf("rozmieszczenie", "sal"), listOf("rozmieszczeniem", "sal"), listOf("znajde", "sale")),
            3 to listOf(listOf("ksero")),
            4 to listOf(listOf("szatnia", "szatnie")))
        private val answers = hashMapOf<Int, Int>(0 to R.array.rooms_answer_0,
            1 to R.array.rooms_answer_1,
            2 to R.array.rooms_answer_2,
            3 to R.array.rooms_answer_3,
            4 to R.array.rooms_answer_4)
    }
}