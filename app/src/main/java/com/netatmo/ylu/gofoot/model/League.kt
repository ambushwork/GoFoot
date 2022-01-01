package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("logo") val logo: String
)
