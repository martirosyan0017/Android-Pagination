package com.example.paginglibrary.base

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
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity() {

    fun createFragment(
        resId: Int,
        fragment: Fragment,
        bundle: Bundle? = null,
        updateScreen: Boolean = false
    ) {
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

    fun showExitDialog(message: String) {

        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
        builder.setPositiveButton(getString(R.string.exit_button)) { _, _ ->
            finish()
        }
        builder.setNegativeButton(
            getString(R.string.cancel),
            DialogInterface.OnClickListener { _, _ -> return@OnClickListener })

        val dialog = builder.create()
        dialog.show()
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
}
