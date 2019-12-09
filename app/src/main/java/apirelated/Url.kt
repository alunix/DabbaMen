package apirelated

class Url {

    companion object {
        const val BASE_URL = "http://experteservices.com"
        const val FOOD_ITEMSLIST_URL =
            "websites/homemakefood/frontend/web/index.php?r=site%2Fitemslist"
        const val LOGIN = "websites/homemakefood/frontend/web/index.php?r=site%2Flogin"
        const val REGISTER = "websites/homemakefood/frontend/web/index.php?r=site%2Fregister"
        const val CHEFS_LIST_URL =
            "/websites/homemakefood/frontend/web/index.php?r=site%2Fitemslistget&itemtype=Lunch&type=veg&lat=17.387140&lang=78.491684"
        /*all images basic path*/
        const val IMAGES_BASICPATH_URL =
            "http://experteservices.com/websites/homemakefood/backend/web/"
        /*get addresses list*/
        const val GET_ADDRESSES_LIST_URL =
            "websites/homemakefood/frontend/web/index.php?r=site/getaddress&userid=2"
        /*add new address*/
        const val ADD_NEWADDRESS_URL =
            "websites/homemakefood/frontend/web/index.php?r=site/addnewaddress"
        /*update address*/
        const val UPDATE_ADDRESS_URL =
            "websites/homemakefood/frontend/web/index.php?r=site/updateaddress"

    }
}