package com.example.paginglibrary.view.activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.paginglibrary.R
import com.example.paginglibrary.view.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*


class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSpannable()
        if (savedInstanceState == null) {
            createFragment(NewsFragment(), savedInstanceState)
        }
    }

    private fun createFragment(fragment: Fragment, bundle: Bundle?) {
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount == 1) {
            showExitDialog(getString(R.string.exit_app))
        } else {
            super.onBackPressed()
        }
    }


    private fun showExitDialog(message: String) {

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setMessage(message)
        builder.setPositiveButton(getString(R.string.exit_button)) { dialog, which ->
            finish()
        }
        builder.setNegativeButton(
            getString(R.string.cancel),
            DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })

        val dialog = builder.create()
        dialog.show()
    }

    private fun setSpannable() {
        val spannable = SpannableString("Daily News")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.NEWS_COLOR)),
            0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val m1TextView: TextView = text_main_activity
        m1TextView.text = spannable
    }
}
