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
import androidx.fragment.app.FragmentManager
import com.example.paginglibrary.R
import com.example.paginglibrary.base.BaseActivity
import com.example.paginglibrary.view.fragment.NewsDetailFragment
import com.example.paginglibrary.view.fragment.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*


class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSpannable()

        if (savedInstanceState == null) {
            createFr(R.id.fragment_container, NewsFragment())
        }
        /*
             createFragment(R.id.fragment_container, NewsFragment(), null)
          }*/
    }

    fun createFr(resId: Int, fragment: Fragment, bundle: Bundle? = null) {
        if (bundle != null) {
            fragment.arguments = bundle
        }
        supportFragmentManager
            .beginTransaction()
            .replace(resId, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount > 0) {
//            supportFragmentManager.popBackStack()
//
//        } else {
//            super.onBackPressed()
//        }

        val visibleFragment = getVisibleFragment()

        if (visibleFragment is NewsFragment && getAllFragments().size == 1) {
            createFr(R.id.fragment_container, NewsDetailFragment(), null)
        } else if (visibleFragment is NewsDetailFragment) {
            showExitDialog("are you want to exit? ")
        } else {
            super.onBackPressed()
        }
    }

    private fun showExitDialog(message: String) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(getString(R.string.exit_button)) { _, _ -> finish() }
        builder.setNegativeButton(getString(R.string.cancel), DialogInterface.OnClickListener { _, _ -> return@OnClickListener })

        val dialog = builder.create()
        dialog.show()
    }

    private fun getAllFragments(): List<Fragment> {
        val fragmentManager = this.supportFragmentManager
        val fragments: List<Fragment> = fragmentManager.getFragments()
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
            0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val m1TextView: TextView = text_main_activity
        m1TextView.text = spannable
    }
}
