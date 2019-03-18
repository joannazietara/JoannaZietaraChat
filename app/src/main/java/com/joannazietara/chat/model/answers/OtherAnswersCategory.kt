package com.joannazietara.chat.model.answers

import com.joannazietara.chat.R

/**
 * Udostępnia odpowiedzi i słowa kluczowe dla kategorii Inne
 */
class OtherAnswersCategory : AnswersCategory {
    /**
     * Zwraca mapę odpowiedzi, gdzie kluczami są indeksy pytań a wartościami identyfikatory zasobu z odpowiedzią
     *
     * @return  mapa odpowiedzi
     */
    override fun getAnswers(): HashMap<Int, Int> {
        return answers
    }

    /**
     * Zwraca mapę słów kluczowych, gdzie kluczami są indeksy pytań a wartościami listy słów kluczowych
     *
     * @return  mapa słów kluczowych
     */
    override fun getKeywords(): HashMap<Int, List<List<String>>> {
        return keywords
    }

    companion object {
        /**
         * Przechowuje mapę słów kluczowych, gdzie kluczami są indeksy pytań a wartości listy słów kluczowych
         * potrzebnych do identyfikacji pytania
         */
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

        /**
         * Przechowuje mapę odpowiedzi, gdzie kluczami są indeksy pytań, a wartościami identyfikatory zasobów z odpowiedziami
         */
        private val answers = hashMapOf(
            0 to R.array.other_answer_0,
            1 to R.array.other_answer_1,
            2 to R.array.other_answer_2,
            3 to R.array.other_answer_3,
            4 to R.array.other_answer_4
        )
    }
}