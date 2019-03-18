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


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    MessageAdapterListener {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(this)

        initRecyclerView()
        initViewModel()

        ivChatSend.setOnClickListener {
            if(!etChatMessage.text.isBlank()) {
                mainViewModel.addUserMessage(etChatMessage.text.toString(), bottomNavigation.selectedItemId)
                etChatMessage.setText("")
            }
        }

        etChatMessage.setOnEditorActionListener { textView, actionId, keyEvent ->
            if(actionId == EditorInfo.IME_ACTION_SEND && !etChatMessage.text.isBlank()) {
                mainViewModel.addUserMessage(etChatMessage.text.toString(), bottomNavigation.selectedItemId)
                etChatMessage.setText("")
            }
            return@setOnEditorActionListener true
        }
    }

    private fun initViewModel() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel.messages.observe(this, Observer<HashMap<Int, ArrayList<ChatMessage>>> {
                messages ->
            updateMessages(messages[bottomNavigation.selectedItemId]?: ArrayList())
        })
    }

    private fun initRecyclerView() {
        val mLayoutManager = LinearLayoutManager(applicationContext)
        mLayoutManager.stackFromEnd = true
        rvChatMessages.layoutManager = mLayoutManager
        rvChatMessages.itemAnimator = DefaultItemAnimator()
    }

    private fun updateMessages(messages: ArrayList<ChatMessage>) {
        if(messages.isEmpty()) {
            tvNoConversationCaption.visibility = View.VISIBLE
            ivNoConversationImage.visibility = View.VISIBLE
        } else {
            tvNoConversationCaption.visibility = View.INVISIBLE
            ivNoConversationImage.visibility = View.INVISIBLE
        }

        rvChatMessages.adapter = MessagesAdapter(this, messages)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val data: HashMap<Int, ArrayList<ChatMessage>> = mainViewModel.messages.value?:HashMap()
        updateMessages(data[item.itemId] ?:ArrayList())
        return true
    }

    override fun onImageClicked() {
        startActivity(Intent(this, ImageShowActivity::class.java))
    }
}
