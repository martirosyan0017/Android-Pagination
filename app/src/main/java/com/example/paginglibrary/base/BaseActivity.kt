package com.example.paginglibrary.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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

        if (existedFragment != null && !updateScreen)
            mFragment = existedFragment

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
}
