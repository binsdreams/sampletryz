package com.bins.data.entity

import com.google.gson.annotations.SerializedName

data class OwnerResponse(
    @SerializedName("id") var id: Int? = 0,
    @SerializedName("avatar_url") var avatar_url: String? = "")

