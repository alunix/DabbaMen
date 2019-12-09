package apirelated.chefs_model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: String, // panja gutta
    @SerializedName("city")
    val city: String, // Hyderabad
    @SerializedName("country")
    val country: String, // India
    @SerializedName("email")
    val email: String, // mouni@mailinator.com
    @SerializedName("image")
    val image: String, // profileimages/1571722998Koala.jpg
    @SerializedName("mobile")
    val mobile: String, // 7893166416
    @SerializedName("state")
    val state: String, // Telangana
    @SerializedName("username")
    val username: String, // mouni
    @SerializedName("zipcode")
    val zipcode: String // 500072
)