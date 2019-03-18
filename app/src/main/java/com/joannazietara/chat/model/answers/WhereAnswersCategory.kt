package com.joannazietara.chat.model.answers

import com.joannazietara.chat.R

/**
 * Udostępnia odpowiedzi i słowa kluczowe dla kategorii Gdzie znajdę?
 */
class WhereAnswersCategory : AnswersCategory {
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
            0 to listOf(listOf("plan", "zajec", "wykladowcow"), listOf("plan", "zajec")),
            1 to listOf(listOf("gdzie", "sylabusy"), listOf("gdzie", "sylabus")),
            2 to listOf(listOf("sesja"), listOf("egzamin"), listOf("egzaminy")),
            3 to listOf(listOf("napic")),
            4 to listOf(listOf("zjesc"))
        )

        /**
         * Przechowuje mapę odpowiedzi, gdzie kluczami są indeksy pytań, a wartościami identyfikatory zasobów z odpowiedziami
         */
        private val answers = hashMapOf(
            0 to R.array.where_answer_0,
            1 to R.array.where_answer_1,
            2 to R.array.where_answer_2,
            3 to R.array.where_answer_3,
            4 to R.array.where_answer_4
        )
    }
}