package com.bins.domain.repository

import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.RepoEntity
import kotlinx.coroutines.channels.ReceiveChannel


interface SquireRepository {

    suspend fun getSquireRepos(): ReceiveChannel<DataEntity<List<RepoEntity>>>

}