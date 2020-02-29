package com.bins.data.api

import com.bins.data.entity.RepoResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface TrendingRepoApi {

    @GET("/orgs/square/repos")
    fun getSquireRepos(): Deferred<List<RepoResponse>>

}