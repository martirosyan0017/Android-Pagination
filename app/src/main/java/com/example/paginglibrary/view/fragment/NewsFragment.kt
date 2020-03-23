package com.example.paginglibrary.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.paginglibrary.BundleKey
import com.example.paginglibrary.listener.NewsItemClickListener
import com.example.paginglibrary.R
import com.example.paginglibrary.base.BaseFragment
import com.example.paginglibrary.model.NewsModel
import com.example.paginglibrary.PagingState.ITEM_END_LOADED
import com.example.paginglibrary.PagingState.ITEM_FRONT_LOADED
import com.example.paginglibrary.base.BaseActivity
import com.example.paginglibrary.view.adapter.NewsAdapter
import com.example.paginglibrary.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : BaseFragment(),
    NewsItemClickListener {
    private lateinit var recyclerView1: RecyclerView
    private lateinit var newsViewModel: NewsViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as BaseActivity).loader(true)
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews()
        initViewModel()
        getNews()
        pagingState()
    }

    private fun findViews() {
        recyclerView1 = recyclerview
    }

    private fun initViewModel() {
        newsViewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
    }

    private fun getNews() {
        val observable = Observer<PagedList<NewsModel>?> {
            it?.let {
                newsPagingAdapter.submitList(it)
            }
        }
        newsViewModel.itemPagedList?.observe(this, observable)
    }

    private fun pagingState() {
        val observer = Observer<String> {
            when (it) {
                ITEM_END_LOADED -> {
                    (activity as BaseActivity).loader(false)
                }
                ITEM_FRONT_LOADED -> {
                    (activity as BaseActivity).loader(false)
                }
            }
        }

        newsViewModel.pagingStateLiveData.observe(this, observer)
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
        openFragment(R.id.fragment_container, NewsDetailFragment(), bundle)
    }
}
