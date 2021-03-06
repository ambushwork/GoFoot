package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: String,
    @SerializedName("code") val code: String,
    @SerializedName("flag") val flag: String
)