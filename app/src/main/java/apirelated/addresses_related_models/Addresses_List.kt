package apirelated.addresses_related_models


import com.google.gson.annotations.SerializedName

data class Addresses_List(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Address List
    @SerializedName("status")
    val status: String // Success
)