package  com.bins.data.mapper

import com.bins.data.entity.RepoResponse
import com.bins.domain.entity.RepoEntity


class ResponseDataToDomainEntityMapper {

    fun mapTo(response: List<RepoResponse>?) :List<RepoEntity>  =mapToList(response)

    private fun mapToList(responses: List<RepoResponse>?)
            : List<RepoEntity> = responses?.map { mapToDomainEntity(it) } ?: emptyList()

    private fun mapToDomainEntity(response: RepoResponse): RepoEntity = RepoEntity(
        id= response.id,
        name= response.name,
        full_name= response.full_name,
        description= response.description,
        avatar_url= response.owner?.avatar_url
    )
}
