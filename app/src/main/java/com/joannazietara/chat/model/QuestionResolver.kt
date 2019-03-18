package com.joannazietara.chat.model

import com.joannazietara.chat.R
import com.joannazietara.chat.model.answers.AnswersCategory
import com.joannazietara.chat.model.answers.OtherAnswersCategory
import com.joannazietara.chat.model.answers.RoomsAnswersCategory
import com.joannazietara.chat.model.answers.WhereAnswersCategory

/**
 * Zajmuje się znalezieniem odpowiedzi na zadane przez użytkownika pytanie
 */
class QuestionResolver {
    /**
     * Znajduje odpowiedz na zadane pytanie w żądanej kategorii. Jeśli nie znajdzie odpowiedzi,
     * zwraca identyfikator do zasobu z napisem informującym o niezrozumieniem pytania
     *
     * @param categoryId    kategoria pytania
     * @param question      tresc pytania uzytkownika
     * @return              identyfikator zasobu z odpowiedzia
     */
    fun getAnswer(categoryId: Int, question: String): Int {
        val questionWords = unifyText(question).split(" ")  // unifkacja pytania i rozdzielenie na slowa
        val answersCat = getProperCategory(categoryId)      // pobranie odpowiedniej klasy z odpowiedziami i slowami kluczowymi
        for (key: Int in answersCat.getKeywords().keys) {   // iteracja po kluczach (indeksach pytan)
            for (keywords: List<String> in answersCat.getKeywords()[key]!!) {   // pobranie slow kluczowych
                if (questionWords.containsAll(keywords))    // jesli wszystkie slowa kluczowe z danej listy znajdują się w pytaniu, najprawdopodobniej to jest nasza odpowiedz
                    return answersCat.getAnswers()[key]!!   // zwroc klucz bedacy indeksem odpowiedzi
            }
        }

        return R.array.no_answer        // nie znaleziono odpowiedzi na pytanie
    }

    /**
     * Zwraca obiekt AnswersCategory dla odpowiedniej kategorii
     *
     * @param categoryId    kategoria pytania
     * @return  obiekt AnswersCategory [RoomsAnswersCategory] lub [WhereAnswersCategory] lub [OtherAnswersCategory]
     */
    private fun getProperCategory(categoryId: Int): AnswersCategory {
        return when (categoryId) {
            R.id.navigation_rooms -> RoomsAnswersCategory()
            R.id.navigation_where -> WhereAnswersCategory()
            else -> OtherAnswersCategory()
        }
    }

    /**
     * Przeprowadza standaryzację tekstu.
     * 1. zamienia wszystkie litery na małe
     * 2. usuwa znaki specjalne - polskie litery, przecinki, kropki itp
     * 3. usuwa zwielokrotnione spacje i zamienia je na pojedyncze
     * 4. usuwa biale znaki z poczatku i konca tekstu
     *
     * @param text  pytanie uzytkownika
     * @return  ujednolicony tekst
     */
    private fun unifyText(text: String): String {
        var question = text.toLowerCase()
        question = replaceSpecialCharacters(question)
        question = question.replace(" +".toRegex(), " ").trim { it <= ' ' }

        return question
    }

    /**
     * Zamienia znaki specjalne w podanym tekscie
     *
     * @param text  Tekst w ktorym nalezy zamienic znaki specjalne
     * @return      Tekst ze zmienionymi znakami specjalnymi
     */
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

    /**
     * Mapa przechowująca znaki specjalne i znaki, na ktore nalezy je zamienic.
     */
    private val characters = hashMapOf(
        'ą' to 'a', 'ę' to 'e', 'ś' to 's', 'ó' to 'o', 'ł' to 'l', 'ź' to 'z', 'ż' to 'z', 'ć' to 'c', 'ń' to 'n',
        '.' to ' ', ',' to ' ', '?' to ' ', '"' to ' ', '(' to ' ', ')' to ' ', '/' to ' ', '\\' to ' ', '\'' to ' ',
        ':' to ' ', ';' to ' ', '-' to ' ', '\ufeff' to ' ', '\ufffe' to ' ', '!' to ' ', '@' to ' ', '#' to ' ',
        '$' to ' ', '%' to ' ', '^' to ' ', '*' to ' ', '_' to ' ', '+' to ' ', '=' to ' ', '{' to ' ', '}' to ' ',
        '[' to ' ', ']' to ' ', '|' to ' ', '<' to ' ', '>' to ' ', '1' to ' ', '2' to ' ', '3' to ' ', '4' to ' ',
        '5' to ' ', '6' to ' ', '7' to ' ', '8' to ' ', '9' to ' ', '0' to ' '
    )
}