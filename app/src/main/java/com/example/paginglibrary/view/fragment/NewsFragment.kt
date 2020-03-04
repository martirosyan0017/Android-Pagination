package com.example.paginglibrary.view.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibrary.utils.BundleKey
import com.example.paginglibrary.utils.NewsItemClickListener
import com.example.paginglibrary.R
import com.example.paginglibrary.base.BaseFragment
import com.example.paginglibrary.model.NewsModel
import com.example.paginglibrary.view.activity.NewsActivity
import com.example.paginglibrary.view.adapter.NewsAdapter
import com.example.paginglibrary.viewmodel.NewsViewModel
import com.github.ybq.android.spinkit.SpinKitView
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment(), NewsItemClickListener {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var progressbar: SpinKitView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews()
        initViewModel()
        getNews()
    }

    private fun findViews() {
        recyclerView1 = recyclerview
        progressbar = progress
    }

    private fun initViewModel() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    private fun getNews() {
        progressbar.visibility = View.VISIBLE
        val observable = Observer<PagedList<NewsModel>?> {
            it?.let {
                progressbar.visibility = View.INVISIBLE
                newsPagingAdapter.submitList(it)
            }
        }
        newsViewModel.itemPagedList?.observe(this, observable)
    }

    private val newsPagingAdapter by lazy {
        val newsPagingAdapter = NewsAdapter(this)
        recyclerView1.layoutManager = GridLayoutManager(context, 1)
        recyclerView1.adapter = newsPagingAdapter
        return@lazy newsPagingAdapter
    }

    override fun onItemClicked(view: View, newsModel: NewsModel) {
        val bundle = Bundle()
        bundle.putParcelable(BundleKey.DETAIL_KEY, newsModel)
        openFragment(R.id.fragment_container, NewsDetailFragment(),bundle)
    }
}
