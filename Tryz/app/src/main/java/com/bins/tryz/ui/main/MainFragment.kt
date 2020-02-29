package com.bins.tryz.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bins.base.ViewModelProviderFactory
import com.bins.tryz.R
import com.bins.tryz.entity.Data
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.main_fragment.*
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
        viewModel.getSquireRepos()
        swipeRefreshLayoutLayout.setOnRefreshListener {
            viewModel.getSquireRepos()
        }

        viewModel.getData().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Data.ERROR -> {
                    var error = it.error
                    swipeRefreshLayoutLayout.isRefreshing = false
                }
                is Data.SUCCESS -> {
                    swipeRefreshLayoutLayout.isRefreshing = false
                    it.data?.let { list -> adapter?.updateList(list) }
                }
            }
        })
    }

}
