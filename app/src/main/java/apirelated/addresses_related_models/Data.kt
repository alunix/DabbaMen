package apirelated.addresses_related_models


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: String, // Banjara hills
    @SerializedName("addressid")
    val addressid: String, // 2
    @SerializedName("addresstype")
    val addresstype: String, // office
    @SerializedName("city")
    val city: String, // Hyderabad
    @SerializedName("country")
    val country: String, // India
    @SerializedName("firstname")
    val firstname: String, // madhavi
    @SerializedName("lastname")
    val lastname: String, // latha
    @SerializedName("state")
    val state: String, // Andhra Pradesh
    @SerializedName("userid")
    val userid: String, // 2
    @SerializedName("zipcode")
    val zipcode: String // 500072
)