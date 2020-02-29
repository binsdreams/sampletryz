package com.bins.data.repository

import com.bins.data.api.TrendingRepoApi
import com.bins.data.mapper.ResponseDataToDomainEntityMapper
import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.RepoEntity
import com.bins.domain.repository.SquireRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class SquireRepositoryImpl(private val api: TrendingRepoApi) : SquireRepository {

   private var mapper = ResponseDataToDomainEntityMapper()

    override suspend fun getSquireRepos(): ReceiveChannel<DataEntity<List<RepoEntity>>> {
        return GlobalScope.produce {
            try {
                val repoResponse = api.getSquireRepos().await()
                send(DataEntity.SUCCESS(mapper.mapTo(repoResponse)))
            } catch (e: Exception) {
                send(DataEntity.ERROR(""+e.message))
            }
        }
    }


}