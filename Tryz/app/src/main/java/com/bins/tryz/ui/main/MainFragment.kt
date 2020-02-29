package com.bins.tryz.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bins.base.ViewModelProviderFactory
import com.bins.tryz.MainActivity
import com.bins.tryz.R
import com.bins.tryz.entity.Data
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.no_network_layout.*
import javax.inject.Inject

class MainFragment : DaggerFragment() {

    lateinit var viewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private var adapter =RepoAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        recyclerViewData.layoutManager = LinearLayoutManager(context)
        recyclerViewData.adapter = adapter
        recyclerViewData.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        adapter.updateList(viewModel.getEmptyListForShimmer())
        viewModel.getSquireRepos(false)
        swipeRefreshLayoutLayout.setOnRefreshListener {
            refresh()
        }

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Data.ERROR -> {
                    var error = it.error
                    swipeRefreshLayoutLayout.isRefreshing = false
                    noNetowrkScreen.visibility = View.VISIBLE
                }
                is Data.SUCCESS -> {
                    noNetowrkScreen.visibility = View.GONE
                    swipeRefreshLayoutLayout.isRefreshing = false
                    it.data?.let { list -> adapter?.updateList(list) }
                }
            }
        })

        retryButton.setOnClickListener{
            refresh()
        }
    }

    private fun refresh(){
        noNetowrkScreen.visibility = View.GONE
        adapter.updateList(viewModel.getEmptyListForShimmer())
        viewModel.getSquireRepos(true)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as MainActivity).updateTitle(R.string.repos)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var layoutManager = recyclerViewData.layoutManager as LinearLayoutManager
        outState.putInt("lastVisiblePosition",layoutManager.findFirstVisibleItemPosition())
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        var visiblePosition = savedInstanceState?.getInt("lastVisiblePosition")
        recyclerViewData.post {
            recyclerViewData.scrollToPosition(visiblePosition?:0)
        }
    }
}
