package com.joannazietara.chat.features.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import com.joannazietara.chat.R
import com.joannazietara.chat.features.main.MainActivity

/**
 * Pierwsza aktywność, pozwalająca zainicjalizować aplikację
 */
class SplashScreenActivity : AppCompatActivity() {
    /**
     * Rozpoczyna tworzenie aktywnosci
     *
     * @param savedInstanceState    zachowuje zapisany stan aktywnosci
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        BigImageViewer.initialize(GlideImageLoader.with(applicationContext))    // inicjalizacja biblioteki obslugującej ładowanie obrazu

        // wyświetlenie głównej aktywności po 3 sekundach
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}