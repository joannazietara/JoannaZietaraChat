package com.joannazietara.chat.features.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.joannazietara.chat.model.ChatMessage
import com.joannazietara.chat.model.QuestionResolver
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * ViewModel do obsługi aktywności MainActivity. Zajmuje się dodawaniem wiadomości do czatu oraz uzyskiwaniem odpowiedzi.
 *
 * @property application    Przechowuje instancję obiektu Aplikacji
 * @constructor             Tworzy nowy obiekt MainViewModel z pusta lista wiadomosci
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *  Obiekt przechowujacy listę wiadomości podzieloną na konkretne kategorie [sale, gdzie znajde, inne]
     */
    val messages: MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>> by lazy { MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>>() }
    /**
     * Uzyskuje odpowiedzi na zadane pytania
     */
    private val questionResolver = QuestionResolver()
    /**
     * Zapewnia liczby losowe
     */
    private val random = Random()

    init {
        messages.value = HashMap()
    }

    /**
     * Dodaje pytanie uzytkownika do listy wiadomosci do odpowiedniej kategorii jak i dodaje do listy wiadomosci
     * odpowiedz uzyskana od chat-bota
     *
     * @param text    tresc pytania uzytkownika
     * @param categoryId    kategoria pytania
     */
    fun addUserMessage(text: String, categoryId: Int) {
        messages.add(categoryId, ChatMessage(text, ChatMessage.USER))
        messages.add(categoryId, ChatMessage(getAnswer(categoryId, text), ChatMessage.BOT))
    }

    /**
     * Przesyla pytanie uzytkownika do QuestionResolver w celu uzyskania odpowiedzi.
     * Otrzymuje tablicę odpowiedzi z ktorych losuje jedną, by urozmaicić styl odpowiadania
     *
     * @param question      tresc pytania uzytkownika
     * @param categoryId    kategoria pytania
     * @return  tresc odpowiedzi
     */
    private fun getAnswer(categoryId: Int, question: String): String {
        val answersId = questionResolver.getAnswer(categoryId, question)
        val answers = getApplication<Application>().resources.getStringArray(answersId)
        val index = random.nextInt(answers.size)

        return answers[index]
    }

    /**
     * Metoda rozszerzająca możliwość zmiennej typu MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>>
     *     na posiadanie modyfikatora add, by w łatwy i szybki sposob dodać wiadomość do listy wiadomosci
     *
     * @param id    kategoria wiadomosci
     * @param item  obiekt z trescia wiadomosci
     */
    private fun MutableLiveData<HashMap<Int, ArrayList<ChatMessage>>>.add(id: Int, item: ChatMessage) {
        val updatedItems = this.value as HashMap

        if (!updatedItems.containsKey(id))
            updatedItems[id] = ArrayList()
        updatedItems[id]!!.add(item)

        this.value = updatedItems
    }
}