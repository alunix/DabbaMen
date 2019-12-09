package apirelated


import apirelated.addresses_related_models.Addresses_List
import apirelated.items_model.FoodItemsAPIResponse
import apirelated.login_register_moel.LoginRegisterResponse
import com.anagha.dabbamen.AddressesList
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface CallApi {

    //@GET(Url.FOOD_ITEMSLIST_URL)
    //query needed if there is any query
    //fun getFoodItemsListApi(): Call<FoodItemsAPIResponse>;
    //model class is needed
    // Observable<FoodItemsAPIResponse>

    /*@GET(Url.CHEFS_LIST_URL)
    //query needed if there is any query
    fun getChefsListApi(@Query("limit") limit: Int):
    //model class is needed
    Observable<ChefsResponse>*/

    @POST(Url.FOOD_ITEMSLIST_URL)
    @FormUrlEncoded
    fun getItemsListPost(
        @Field("itemtype") itemtype: String,
        @Field("type") type: String,
        @Field("lat") lat: String,
        @Field("lang") lang: String,
        @Field("day") day: String,
        @Field("varietyname") varietyname: String
    ): Call<FoodItemsAPIResponse>;

    @POST(Url.LOGIN)
    @FormUrlEncoded
    fun loginPost(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("role") role: String
    ): Call<LoginRegisterResponse>;

     @POST(Url.REGISTER)
     @FormUrlEncoded
     fun registerPost(
         @Field("username") username: String,
         @Field("email") email: String,
         @Field("mobile") mobile: String,
         @Field("password") password: String,
         @Field("role") role: String
     ):Call<LoginRegisterResponse>;


    @GET(Url.GET_ADDRESSES_LIST_URL)
    fun addresseslistGet():Call<Addresses_List>;



}