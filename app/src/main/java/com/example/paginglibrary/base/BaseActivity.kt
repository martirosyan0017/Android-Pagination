package com.example.paginglibrary.base

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.paginglibrary.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_layout.*

open class BaseActivity : AppCompatActivity() {

    fun createFragment(
        resId: Int,
        fragment: Fragment,
        bundle: Bundle? = null,
        updateScreen: Boolean = false) {

        var mFragment = fragment
        val findFragment: Fragment? = supportFragmentManager.findFragmentById(resId)
        val existedFragment: Fragment? = supportFragmentManager.findFragmentByTag(mFragment.javaClass.name)

        if (existedFragment != null && !updateScreen) {
            mFragment = existedFragment
        }

        if (bundle != null)
            mFragment.arguments = bundle

        if (findFragment == null) {
            supportFragmentManager
                .beginTransaction()
                .add(resId, mFragment, mFragment.javaClass.name)
                .addToBackStack(null)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(resId, mFragment, mFragment.javaClass.name)
                .addToBackStack(null)
                .commit()
        }
    }

    fun setSpannable() {
        val spannable = SpannableString("Daily News")
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.NEWS_COLOR)),
            0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val m1TextView: TextView = text_main_activity
        m1TextView.text = spannable
    }

    fun loader(state: Boolean) {
        if (state) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.GONE
        }
    }
}
