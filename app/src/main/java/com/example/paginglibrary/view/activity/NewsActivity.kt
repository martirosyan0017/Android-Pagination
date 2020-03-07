package com.example.paginglibrary.view.activity

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.paginglibrary.R
import com.example.paginglibrary.base.BaseActivity
import com.example.paginglibrary.view.fragment.NewsDetailFragment
import com.example.paginglibrary.view.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class NewsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSpannable()

        createFragment(R.id.fragment_container, NewsFragment(), null,false)
    }

    override fun onBackPressed() {
        val visibleFragment = getVisibleFragment()

        if (visibleFragment is NewsDetailFragment && getAllFragments().size == 1) {
            createFragment(R.id.fragment_container, NewsFragment(), null,true)
        } else if (visibleFragment is NewsFragment) {
            showExitDialog(getString(R.string.exit_app))
        } else {
            super.onBackPressed()
        }
    }

    private fun getAllFragments(): List<Fragment> {
        val fragmentManager = this.supportFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments
        return fragments
    }

    private fun getVisibleFragment(): Fragment? {
        val fragmentManager = this.supportFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments

        for (fragment in fragments) {
            if (fragment.isVisible) return fragment
        }
        return null
    }

    private fun setSpannable() {
        val spannable = SpannableString("Daily News")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.NEWS_COLOR)),
            0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        val m1TextView: TextView = text_main_activity
        m1TextView.text = spannable
    }
}
