package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import apirelated.addresses_related_models.Data
import com.anagha.dabbamen.R
import com.nostra13.universalimageloader.core.DisplayImageOptions

class AddressItemsListAdapter(val list: List<Data>) :
    RecyclerView.Adapter<AddressItemsListAdapter.AddressItemViewHolder>() {
    private var options: DisplayImageOptions? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressItemViewHolder {
        val myViewHolder: AddressItemViewHolder
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(
            R.layout.select_delivery_address_row, parent, false
        )
        myViewHolder = AddressItemViewHolder(itemLayoutView)

        /* val config = ImageLoaderConfiguration.Builder(parent.context)
             .memoryCache(UsingFreqLimitedMemoryCache(25 * 1024 * 1024))// 25 Mb (delete most not used image)
             .build()
         ImageLoader.getInstance().init(config)*/
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(myViewHolder: AddressItemViewHolder, position: Int) {
        val myAddress = list[position]
        myViewHolder.mFirstNameView?.text = myAddress.firstname+" "+myAddress.lastname
       // myViewHolder.mphoneNoView?.text = myAddress.chef
        myViewHolder.mAddressView?.text = myAddress.address
        myViewHolder.mCountryView?.text = myAddress.country
        myViewHolder.mStateNameView?.text = myAddress.state
        myViewHolder.mCityView?.text = myAddress.city
        myViewHolder.mZipcodeView?.text = myAddress.zipcode
        //myViewHolder.mFirstNameView?.text = myOrder.chef


        /* myViewHolder.mFoodItemnameView?.text = myOrder.itemname
         if (myOrder.type == "veg") {
             myViewHolder.mFoodItemnamePrice_VNIndicator?.setCompoundDrawablesWithIntrinsicBounds(
                 R.drawable.veg,
                 0,
                 0,
                 0
             )
             myViewHolder.mFoodItemnamePrice_VNIndicator?.text = myOrder.currency + myOrder.price
         } else {
             myViewHolder.mFoodItemnamePrice_VNIndicator?.setCompoundDrawablesWithIntrinsicBounds(
                 R.drawable.nonveg,
                 0,
                 0,
                 0
             )
             myViewHolder.mFoodItemnamePrice_VNIndicator?.text = myOrder.currency + myOrder.price
         }*/

    }

    inner class AddressItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var mFirstNameView: TextView? = null
        internal var mphoneNoView: TextView? = null
        internal var mAddressView: TextView? = null
        internal var mAddressTypeView: TextView? = null
        internal var mCountryView: TextView? = null
        internal var mStateNameView: TextView? = null
        internal var mCityView: TextView? = null
        internal var mZipcodeView: TextView? = null


        /* internal var mFoodItemnamePrice_VNIndicator: TextView? = null
         internal var mFoodItemnameView: TextView? = null
         internal var mFoodItemImageView: ImageView? = null*/

        init {
            mFirstNameView = itemView.findViewById(R.id.addresseslist_firstnameTV)
            mphoneNoView = itemView.findViewById(R.id.addresseslist_phonenoTV)
            mAddressView = itemView.findViewById(R.id.addresseslist_addresslineoneTV)
            mCountryView = itemView.findViewById(R.id.addresseslist_countryTV)
            mStateNameView = itemView.findViewById(R.id.addresseslist_stateTV)
            mCityView = itemView.findViewById(R.id.addresseslist_addresscityTV)
            mZipcodeView = itemView.findViewById(R.id.addresses_pincodeTV)
            mAddressTypeView = itemView.findViewById(R.id.addresseslist_firstnameTV)


            /* mFoodItemnameView = itemView.findViewById(R.id.product_row_fooditemnameTV)
             mFoodItemnamePrice_VNIndicator = itemView.findViewById(R.id.product_priceTV)
             mFoodItemImageView = itemView.findViewById(R.id.product_list_row_productIM)*/

        }


    }


}