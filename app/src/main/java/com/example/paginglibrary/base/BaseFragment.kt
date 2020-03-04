package com.example.paginglibrary.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.paginglibrary.R
import com.example.paginglibrary.view.activity.NewsActivity

open class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    fun openFragment(resId: Int, fragment: Fragment, bundle: Bundle? = null) {
        val parentActivity: BaseActivity? = activity as BaseActivity?
        parentActivity?.createFragment(resId, fragment, bundle)
    }
}
