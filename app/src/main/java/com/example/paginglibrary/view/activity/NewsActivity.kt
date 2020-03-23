package com.example.paginglibrary.view.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.paginglibrary.R
import com.example.paginglibrary.base.BaseActivity
import com.example.paginglibrary.extentions.*
import com.example.paginglibrary.view.fragment.NewsDetailFragment
import com.example.paginglibrary.view.fragment.NewsFragment

class NewsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSpannable()
        if (savedInstanceState == null) {
            createFragment(R.id.fragment_container, NewsFragment(), null, true)
        }
    }

    override fun onBackPressed() {
        val visibleFragment = getVisibleFragment()

        if (visibleFragment is NewsDetailFragment && getAllFragments().size == 1) {
            createFragment(R.id.fragment_container, NewsFragment(), null, true)
        } else if (visibleFragment is NewsFragment) {
            showExitDialog(getString(R.string.exit_app))
        } else {
            super.onBackPressed()
        }
    }

    private fun getAllFragments(): List<Fragment> {
        val fragmentManager = this.supportFragmentManager
        return fragmentManager.fragments
    }

    private fun getVisibleFragment(): Fragment? {
        val fragmentManager = this.supportFragmentManager
        val fragments: List<Fragment> = fragmentManager.fragments

        for (fragment in fragments) {
            if (fragment.isVisible)
                return fragment
        }
        return null
    }

    private val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (!checkNetworkState(applicationContext)) {
                showNetworkDialog()
            } else {
                    showProgressDialog()
            }
        }
    }

    override fun onPause() {

        //  remove broadcast receiver when activity stops
        unregisterReceiver(broadCastReceiver)
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        //  register broadcast receiver after starting activity
        val intentFilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(broadCastReceiver, intentFilter)
    }
}
