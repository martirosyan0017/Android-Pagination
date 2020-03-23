package com.example.paginglibrary.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.paginglibrary.R
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SplashScreen : AppCompatActivity() {

    private var handler: Handler? = null
    private lateinit var runnable: Runnable
    private val DELAY: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        setPostDelay()
    }

    private fun setPostDelay() {
        handler = Handler()

        runnable = Runnable {
            if (!isFinishing) {
                goToMain()
            }
        }
        handler!!.postDelayed(runnable, DELAY)
    }

    public override fun onDestroy() {
        handler?.removeCallbacks(runnable)
        super.onDestroy()
    }

    private fun goToMain() {
        val intent = Intent(this, NewsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        finish()
    }
}