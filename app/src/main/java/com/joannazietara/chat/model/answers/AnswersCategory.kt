package com.joannazietara.chat.model.answers

/**
 * Intefejs przekazujący odpowiedzi i slowa kluczowe, na podstawie ktorych uzyskiwana jest odpowiedz
 */
interface AnswersCategory {
    /**
     * Zwraca mapę odpowiedzi, gdzie kluczami są indeksy pytań a wartościami identyfikatory zasobu z odpowiedzią
     *
     * @return  mapa odpowiedzi
     */
    fun getAnswers(): HashMap<Int, Int>
    /**
     * Zwraca mapę słów kluczowych, gdzie kluczami są indeksy pytań a wartościami listy słów kluczowych
     *
     * @return  mapa słów kluczowych
     */
    fun getKeywords(): HashMap<Int, List<List<String>>>
}