package com.joannazietara.chat.features.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.joannazietara.chat.R
import kotlinx.android.synthetic.main.activity_main.*
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joannazietara.chat.model.ChatMessage
import android.content.Intent
import android.content.ContentResolver
import android.content.res.AssetManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.content.FileProvider
import com.joannazietara.chat.features.ImageShowActivity
import java.io.InputStream


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation.setOnNavigationItemSelectedListener(this)

        initRecyclerView()
        initViewModel()

        ivChatSend.setOnClickListener {
//            val am: AssetManager = this.getApplicationContext().getAssets()
//            val iss: InputStream = am.open("rozmieszczenie_sal.jpg")
//            val myNewImage: Bitmap = BitmapFactory.decodeStream(iss);
//
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.setType("image/*")
////            intent.putExtra(Intent.EXTRA_STREAM, resourceToUri(R.drawable.rozmieszczenie_sal))
//
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            val uri: Uri = Uri.parse("content://com.joannazietara.chat.provider/rozmieszczenie_sal.jpg");
////            intent.putExtra(Intent.EXTRA_STREAM, uri)
////            FileProvider.getUriForFile(applicationContext, applicationContext.packageName + ".provider", createImageFile());
//            intent.setDataAndType( uri, "image/*")
//            startActivity(Intent.createChooser(intent, "Share image using"));
            startActivity(Intent(this, ImageShowActivity::class.java))
//            mainViewModel.addUserMessage(etChatMessage.text.toString(), bottomNavigation.selectedItemId)
//            etChatMessage.setText("")
        }
    }

    fun resourceToUri(resID:Int): Uri {
        val resources: Resources = applicationContext.resources;
        return Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(resID))
            .appendPath(resources.getResourceTypeName(resID))
            .appendPath(resources.getResourceEntryName(resID))
            .build();
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

        rvChatMessages.adapter = MessagesAdapter(messages)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val data: HashMap<Int, ArrayList<ChatMessage>> = mainViewModel.messages.value?:HashMap()
        updateMessages(data[item.itemId] ?:ArrayList())
        return true
    }
}
