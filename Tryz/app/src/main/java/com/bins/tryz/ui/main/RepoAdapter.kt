package com.bins.tryz.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bins.tryz.R
import com.bins.tryz.databinding.RepoItemBinding
import com.bins.tryz.entity.SquireRepo

class TrendingAdapter : RecyclerView.Adapter<DataViewHolder>() {

    private var dataList = mutableListOf<SquireRepo?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val dataItemBinding: RepoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.repo_item,
            parent, false
        )
        return DataViewHolder(dataItemBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])

    }

    override fun getItemCount(): Int = dataList.count()

    fun updateList(list: List<SquireRepo?>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

}

class DataViewHolder(private var binding: RepoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dataItem: SquireRepo?) {
        binding.trendingRepo = RepoItemVM(dataItem)
    }
}