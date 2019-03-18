package com.joannazietara.chat.model.answers

import com.joannazietara.chat.R

/**
 * Udostępnia odpowiedzi i słowa kluczowe dla kategorii Sale
 */
class RoomsAnswersCategory : AnswersCategory {
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
            0 to listOf(
                listOf("gdzie", "dziekanat"),
                listOf("jakim", "miejscu", "dziekanat"),
                listOf("jakim", "budynku", "dziekanat")
            ),
            1 to listOf(
                listOf("kiedy", "dziekanat"),
                listOf("jakich", "godzinach", "dziekanat"),
                listOf("jakie", "dni", "dziekanat"),
                listOf("godziny", "dziekanat")
            ),
            2 to listOf(listOf("rozmieszczenie", "sal"), listOf("rozmieszczeniem", "sal"), listOf("znajde", "sale")),
            3 to listOf(listOf("ksero")),
            4 to listOf(listOf("szatnia", "szatnie"))
        )

        /**
         * Przechowuje mapę odpowiedzi, gdzie kluczami są indeksy pytań, a wartościami identyfikatory zasobów z odpowiedziami
         */
        private val answers = hashMapOf(
            0 to R.array.rooms_answer_0,
            1 to R.array.rooms_answer_1,
            2 to R.array.rooms_answer_2,
            3 to R.array.rooms_answer_3,
            4 to R.array.rooms_answer_4
        )
    }
}