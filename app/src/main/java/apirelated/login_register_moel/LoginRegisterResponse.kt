package apirelated.login_register_moel


import com.google.gson.annotations.SerializedName

data class LoginRegisterResponse(
    @SerializedName("email")
    val email: String, // manasa@mailinator.com
    @SerializedName("id")
    val id: Int, // 19
    @SerializedName("message")
    val message: String, // login Successfully
    @SerializedName("mobile")
    val mobile: String, // 7894561253
    @SerializedName("status")
    val status: String, // success
    @SerializedName("username")
    val username: String // manasa
)