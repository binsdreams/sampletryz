package com.bins.tryz.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bins.tryz.R
import com.bins.tryz.databinding.RepoItemBinding
import com.bins.tryz.entity.SquireRepo
import com.facebook.shimmer.ShimmerFrameLayout

const val SHIMMER = 0
const val DATA = 1
class RepoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = mutableListOf<SquireRepo?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if(SHIMMER == viewType){
            var view = LayoutInflater.from(parent.context).inflate(R.layout.shimmer_item,null)
            return ShimmerViewHolder(view)

        }else {
            val dataItemBinding: RepoItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.repo_item,
                parent, false
            )
            return DataViewHolder(dataItemBinding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(dataList[position] == null) SHIMMER else DATA
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is DataViewHolder){
            holder.bind(dataList[position])
        }else  if(holder is ShimmerViewHolder){
            holder.bind()
        }
    }

    override fun getItemCount(): Int = dataList.count()

    fun updateList(list: List<SquireRepo?>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }
}

class ShimmerViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(){
        (view as ShimmerFrameLayout).startShimmerAnimation()
    }
}

class DataViewHolder(private var binding: RepoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(dataItem: SquireRepo?) {
        binding.trendingRepo = RepoItemVM(dataItem)
    }
}