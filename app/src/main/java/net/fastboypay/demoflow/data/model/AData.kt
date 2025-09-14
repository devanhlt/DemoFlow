package net.fastboypay.demoflow.data.model

import com.google.gson.annotations.SerializedName

data class AData(
    @SerializedName("a1")
    val a1: String = "",
    @SerializedName("a2")
    val a2: String = "",
    @SerializedName("a3")
    val a3: String = "",
    @SerializedName("a4")
    val a4: String = ""
)