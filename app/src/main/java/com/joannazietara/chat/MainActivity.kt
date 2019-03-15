package com.joannazietara.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_rooms -> {
                tvChatAnswer.visibility = View.INVISIBLE
                tvUserQuestion.visibility = View.INVISIBLE
                tvNoConversationCaption.visibility = View.VISIBLE
                ivNoConversationImage.visibility = View.VISIBLE
                return true
            }
            R.id.navigation_where -> {
                tvChatAnswer.visibility = View.INVISIBLE
                tvUserQuestion.visibility = View.INVISIBLE
                tvNoConversationCaption.visibility = View.VISIBLE
                ivNoConversationImage.visibility = View.VISIBLE
                return true
            }
            R.id.navigation_other -> {
                tvChatAnswer.visibility = View.VISIBLE
                tvUserQuestion.visibility = View.VISIBLE
                tvNoConversationCaption.visibility = View.INVISIBLE
                ivNoConversationImage.visibility = View.INVISIBLE
                return true
            }
        }
        return false
    }
}
