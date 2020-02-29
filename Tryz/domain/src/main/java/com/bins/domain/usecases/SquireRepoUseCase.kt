package com.bins.domain.usecases

import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.RepoEntity
import kotlinx.coroutines.channels.ReceiveChannel


interface SquireRepoUseCase {

    suspend fun getSquireRepos(): ReceiveChannel<DataEntity<List<RepoEntity>>>

}