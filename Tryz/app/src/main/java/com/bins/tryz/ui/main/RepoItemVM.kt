package com.bins.tryz.ui.main

import androidx.databinding.BaseObservable
import com.bins.tryz.entity.SquireRepo

class RepoItemVM constructor(private var repoItem: SquireRepo?) : BaseObservable() {

    fun getName(): String? {
        return repoItem?.name
    }

    fun getFullName(): String? {
        return repoItem?.full_name
    }

    fun getDescription(): String? {
        return repoItem?.description
    }


    fun getAvatar(): String? {
        return repoItem?.avatar_url
    }
}
