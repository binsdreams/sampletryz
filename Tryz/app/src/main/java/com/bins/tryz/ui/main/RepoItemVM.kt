package com.bins.tryz.ui.main

import android.content.res.Resources
import androidx.databinding.BaseObservable
import com.bins.tryz.R
import com.bins.tryz.entity.SquireRepo

class RepoItemVM constructor(private var repoItem: SquireRepo?,private var res: Resources?) : BaseObservable() {

    fun getName(): String? {
        return repoItem?.name
    }

    fun getFullName(): String? {
        return repoItem?.full_name
    }

    fun getDescription(): String? {
        return if(repoItem?.description.isNullOrEmpty()) res?.getString(R.string.noDescription) else repoItem?.description
    }


    fun getAvatar(): String? {
        return repoItem?.avatar_url
    }
}
