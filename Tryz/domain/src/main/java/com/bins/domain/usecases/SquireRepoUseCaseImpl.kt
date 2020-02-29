package com.bins.domain.usecases

import com.bins.domain.entity.DataEntity
import com.bins.domain.entity.RepoEntity
import com.bins.domain.repository.SquireRepository
import kotlinx.coroutines.channels.ReceiveChannel

/**
 * if there is any use case we should implement here, currently we dont have any.
 */
class SquireRepoUseCaseImpl(private val squireRepository: SquireRepository) : SquireRepoUseCase {

    override suspend fun getSquireRepos(): ReceiveChannel<DataEntity<List<RepoEntity>>> {
        return squireRepository.getSquireRepos()
    }


}