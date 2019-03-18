package com.joannazietara.chat.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joannazietara.chat.R
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.joannazietara.chat.features.ImageShowActivity
import com.joannazietara.chat.model.ChatMessage

/**
 * Główna aktywność w której znajduje się okno czatu
 */
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    MessageAdapterListener {

    /**
     *  Przechowuje instację do klasy MainViewModel
     */
    private lateinit var mainViewModel: MainViewModel

    /**
     * Rozpoczyna tworzenie aktywnosci
     *
     * @param savedInstanceState    zachowuje zapisany stan aktywnosci
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(this)

        initRecyclerView()
        initViewModel()

        ivChatSend.setOnClickListener {
            if (!etChatMessage.text.isBlank()) {    // obsluga pytania tylko jesli pole pytania nie jest puste
                mainViewModel.addUserMessage(etChatMessage.text.toString(), bottomNavigation.selectedItemId)    // dodanie wiadomosci
                etChatMessage.setText("")   // wyczyszczenie pola wiadomosci
            }
        }

        // obsluga przycisku 'wyslij' na klawiaturze
        etChatMessage.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEND && !etChatMessage.text.isBlank()) {  // jesli przycisk nacisniety i pole pytania nie jest puste
                mainViewModel.addUserMessage(etChatMessage.text.toString(), bottomNavigation.selectedItemId)
                etChatMessage.setText("")
            }
            return@setOnEditorActionListener true
        }
    }

    /**
     *  Inicjalizacja klasy ViewModel i obserwowanie zmian w zmiennej messages
     */
    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel.messages.observe(this, Observer<HashMap<Int, ArrayList<ChatMessage>>> { messages ->
            updateMessages(messages[bottomNavigation.selectedItemId] ?: ArrayList())
        })
    }

    /**
     * Inicjalizacja komponenty RecyclerView, by używał domyślnych animacji, liniowego menedżera oraz dodawał kolejne obiekty od dołu
     */
    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(applicationContext)
        mLayoutManager.stackFromEnd = true
        rvChatMessages.layoutManager = mLayoutManager
        rvChatMessages.itemAnimator = DefaultItemAnimator()
    }

    /**
     * Aktualizuje listę wiadomosci w oknie czatu. Jeśli nie ma żadnych wiadomości, pokazuje informacje,
     * że nie zadano jeszcze żadnych pytań.
     * Tworzy również adapter dla komponentu RecyclerView z przekazanymi wiadomościami
     *
     * @param messages  lista wiadomosci czatu do zaktualizowania
     */
    private fun updateMessages(messages: ArrayList<ChatMessage>) {
        if (messages.isEmpty()) {
            tvNoConversationCaption.visibility = View.VISIBLE
            ivNoConversationImage.visibility = View.VISIBLE
        } else {
            tvNoConversationCaption.visibility = View.INVISIBLE
            ivNoConversationImage.visibility = View.INVISIBLE
        }

        rvChatMessages.adapter = MessagesAdapter(this, messages)
    }

    /**
     * Obsługuje kliknięcia w elementy nawigacji znajdujące się na dole aktywności.
     * Pobiera wiadoomości z danej kategorii z ViewModel i wyświetla na czacie
     *
     * @param item  kliknięty element
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val data: HashMap<Int, ArrayList<ChatMessage>> = mainViewModel.messages.value ?: HashMap()
        updateMessages(data[item.itemId] ?: ArrayList())
        return true
    }

    /**
     * Obsługuje kliknięcie w obrazek przesłany przez chat-bota. Otwiera aktywność wyświetlająca obraz
     */
    override fun onImageClicked() {
        startActivity(Intent(this, ImageShowActivity::class.java))
    }
}
