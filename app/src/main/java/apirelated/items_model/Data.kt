package apirelated.items_model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("chef")
    val chef: String, // mouni
    @SerializedName("currency")
    val currency: String, // ₹
    @SerializedName("image")
    val image: String, // itemimages/1571723565desert.jpg
    @SerializedName("itemname")
    val itemname: String, // veg noodles
    @SerializedName("itemtype")
    val itemtype: String, // Lunch
    @SerializedName("itemvarietyname")
    val itemvarietyname: String, // Chineese
    @SerializedName("price")
    val price: String, // 50.00
    @SerializedName("type")
    val type: String // veg
) 