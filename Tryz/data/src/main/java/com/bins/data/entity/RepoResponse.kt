package com.bins.data.entity

import com.google.gson.annotations.SerializedName

data class RepoResponse(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("name") var name: String? = "",
    @SerializedName("full_name") var full_name: String? = "",
    @SerializedName("description") var description: String? = "",
    @SerializedName("owner") var owner: OwnerResponse?=null)

