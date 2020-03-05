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

class SplashScreen : AppCompatActivity() {

    private var handler: Handler? = null
    private lateinit var runnable: Runnable
    private val DELAY: Long = 1500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        getSpannable()
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

    private fun getSpannable() {
        val spannable = SpannableString("Daily News")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.NEWS_COLOR)),
            0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val mTextView: TextView = text_view
        mTextView.text = spannable
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