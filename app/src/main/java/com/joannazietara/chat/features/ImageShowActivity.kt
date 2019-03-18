package com.joannazietara.chat.features

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joannazietara.chat.R
import com.github.piasy.biv.view.BigImageView
import android.content.ContentResolver

/**
 * Aktywność pokazująca obraz, ktory został klikniety przez uzytkownika
 */
class ImageShowActivity : AppCompatActivity() {
    /**
     * Rozpoczyna tworzenie aktywnosci. Ładuje obraz do komponentu wyswietlajacego obraz
     *
     * @param savedInstanceState    zachowuje zapisany stan aktywnosci
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_show)

        val bigImageView = findViewById<BigImageView>(R.id.mBigImage)
        bigImageView.showImage(resourceToUri(R.drawable.rozmieszczenie_sal))
    }

    /**
     * Konwersja sciezki obrazu z zasobów do obiektu Uri
     *
     * @param resID identyfikator zasobu
     * @return  obiekt Uri ze sciezka do zasobu
     */
    private fun resourceToUri(resID: Int): Uri {
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(resID) + '/'.toString() +
                    resources.getResourceTypeName(resID) + '/'.toString() +
                    resources.getResourceEntryName(resID)
        )
    }
}
