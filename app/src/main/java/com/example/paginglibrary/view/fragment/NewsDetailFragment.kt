package com.example.paginglibrary.view.fragment

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import coil.size.Scale
import com.example.paginglibrary.R
import com.example.paginglibrary.base.BaseFragment
import com.example.paginglibrary.model.NewsModel
import com.example.paginglibrary.utils.BundleKey
import kotlinx.android.synthetic.main.fragment_news_detail.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewsDetailFragment : BaseFragment() {

    private var model: Any? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = arguments?.getParcelable(BundleKey.DETAIL_KEY)
        configure(model)
    }

    private fun configure(model: Any?) {
        model.let {
            if (model is NewsModel) {
                imageView.load(model.fields?.thumbnail) {
                    crossfade(750)
                    placeholder(R.drawable.no_internet_icon)
                    scale(Scale.FILL)
                }
                news_section_name_txt.text = model.sectionName
                news_title_txt.text = model.webTitle
                type_.text = model.type
                getCurrentDateString(model)
            }
        }
    }

    private fun getCurrentDateString(model: NewsModel) {
        var df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()) as DateFormat
        val d: Date? = df.parse(model.webPublicationDate)
        df = SimpleDateFormat("MMM d ,   h:mm a", Locale.getDefault())
        news_date_txt.text = df.format(d!!)

    }
}
