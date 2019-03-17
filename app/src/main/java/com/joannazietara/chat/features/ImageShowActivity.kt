package com.joannazietara.chat.features

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joannazietara.chat.R
import com.github.piasy.biv.view.BigImageView
import android.content.ContentResolver





class ImageShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_show)

        val bigImageView = findViewById(R.id.mBigImage) as BigImageView
        bigImageView.showImage(resourceToUri(R.drawable.rozmieszczenie_sal))
    }

    fun resourceToUri(resID:Int):Uri {
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
            getResources().getResourcePackageName(resID) + '/'.toString() +
            getResources().getResourceTypeName(resID) + '/'.toString() +
            getResources().getResourceEntryName(resID)
        )
    }
}
