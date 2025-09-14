package net.fastboypay.demoflow.data.model

import com.google.gson.annotations.SerializedName

data class BData(
    @SerializedName("b1")
    val b1: String = "",
    @SerializedName("b2")
    val b2: String = "",
    @SerializedName("b3")
    val b3: String = ""
)
