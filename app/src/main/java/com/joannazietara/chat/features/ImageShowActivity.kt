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

        val bigImageView = findViewById<BigImageView>(R.id.mBigImage)
        bigImageView.showImage(resourceToUri(R.drawable.rozmieszczenie_sal))
    }

    private fun resourceToUri(resID: Int): Uri {
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(resID) + '/'.toString() +
                    resources.getResourceTypeName(resID) + '/'.toString() +
                    resources.getResourceEntryName(resID)
        )
    }
}
