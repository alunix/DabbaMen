package apirelated.chefs_model


import com.google.gson.annotations.SerializedName

data class ChefsResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Users List
    @SerializedName("status")
    val status: String // Success
)