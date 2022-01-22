package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class Body<T>(
    @SerializedName("get") val get: String,
    @SerializedName("paging") val paging: Paging?,
    @SerializedName("response") val response: List<T>
)