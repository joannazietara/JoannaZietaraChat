package com.joannazietara.chat.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joannazietara.chat.R
import com.joannazietara.chat.model.ChatMessage

/**
 * Adapter dla komponentu RecyclerView pozwalający wyswietlać dymki poszczegolnych wiadomosci
 *
 * @property messages  lista wiadomosci
 * @property listener  Listener, nasluchujacy, czy uzytkownik kliknal w obrazek
 * @constructor     Tworzy adapter z podanymi wiadomosciami
 */
class MessagesAdapter(private val listener: MessageAdapterListener, private val messages: List<ChatMessage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * Zwraca typ widoku dla danej pozycji w liscie wiadomosci
     *
     * @param position  pozycja w liscie wiadomosci
     * @return      typ danej pozycji w liscie wiadomosci
     */
    override fun getItemViewType(position: Int): Int {
        return messages[position].author
    }

    /**
     * Tworzy obiekt ViewHolder dla odpowiedniego typu widoku
     *
     * @param parent    rodzic widoku
     * @param viewType  typ widoku dla aktualnej pozycji
     * @return  obiekt ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ChatMessage.USER) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.message_user, parent, false)
            UserMessageViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.message_bot, parent, false)
            BotMessageViewHolder(listener, view)
        }
    }

    /**
     * Oblicza ilosc wiadomosci
     *
     * @return  ilosc wiadomosci
     */
    override fun getItemCount(): Int {
        return messages.size
    }

    /**
     * Przydziela informacje do widoku
     *
     * @param holder    obiekt ViewHolder o odpowiednim typie dla aktualnej wiadomosci
     * @param position  pozycja wiadomosci w liscie wiadomosci
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ChatMessage.USER) {
            val viewHolder: UserMessageViewHolder = holder as UserMessageViewHolder
            viewHolder.tvMessage.text = messages[position].message
        } else {
            val viewHolder: BotMessageViewHolder = holder as BotMessageViewHolder
            if (messages[position].message.contains("/drawable")) {
                viewHolder.ivChatImage.visibility = View.VISIBLE
                viewHolder.tvMessage.visibility = View.GONE
            } else {
                viewHolder.ivChatImage.visibility = View.GONE
                viewHolder.tvMessage.visibility = View.VISIBLE
                holder.tvMessage.text = messages[position].message
            }
        }
    }
}

/**
 * ViewHolder dla wiadomosci uzytkownika
 *
 * @property itemView   widok dla tego ViewHoldera
 */
class UserMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Komponent TextView wyswietlajacy wiadomosc
     */
    var tvMessage: TextView = view.findViewById(R.id.tvChatMessage)
}

/**
 * ViewHolder dla wiadomosci chat-bota
 *
 * @property listener   obsługa klikniecia w obraz
 * @property itemView   widok dla tego ViewHoldera
 * @constructor     Dodanie listenera do komponentu ImageView [ivChatImage]
 */
class BotMessageViewHolder(private val listener: MessageAdapterListener, view: View) : RecyclerView.ViewHolder(view) {
    /**
     * Komponent TextView wyswietlajacy wiadomosc
     */
    var tvMessage: TextView = view.findViewById(R.id.tvChatMessage)
    /**
     * Komponent ImageView wyswietlajacy obraz
     */
    var ivChatImage: ImageView = view.findViewById(R.id.ivChatImage)

    init {
        ivChatImage.setOnClickListener { listener.onImageClicked() }
    }
}

/**
 * Interfejs obslugujacy klikniecie w obrazek w wiadomosci
 */
interface MessageAdapterListener {
    /**
     * Obsługuje klikniecie w obrazie w wiadomosci
     */
    fun onImageClicked()
}