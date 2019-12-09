package apirelated.items_model


import com.google.gson.annotations.SerializedName

data class FoodItemsAPIResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("message")
    val message: String, // Items List
    @SerializedName("status")
    val status: String // Success
)