package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import apirelated.items_model.Data
import com.anagha.dabbamen.R
import com.nostra13.universalimageloader.core.DisplayImageOptions
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import apirelated.Url
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration




class FoodItemsListAdapter(val list: List<Data>) : RecyclerView.Adapter<FoodItemsListAdapter.FoodItemViewHolder>() {
    private var options: DisplayImageOptions? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val myViewHolder: FoodItemViewHolder
        val itemLayoutView = LayoutInflater.from(parent.context).inflate(
            R.layout.product_row, parent, false)
        myViewHolder = FoodItemViewHolder(itemLayoutView)

        val config = ImageLoaderConfiguration.Builder(parent.context)
            .memoryCache(UsingFreqLimitedMemoryCache(25 * 1024 * 1024))// 25 Mb (delete most not used image)
            .build()
        ImageLoader.getInstance().init(config)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(myViewHolder: FoodItemViewHolder, position: Int) {
        val myOrder = list[position]

        options = DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.epooja_loader)
            .showImageForEmptyUri(com.anagha.dabbamen.R.drawable.epooja_loader)
            .showImageOnFail(R.drawable.epooja_loader)
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .considerExifParams(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .build()

        myViewHolder. mChefNameView?.text = myOrder.chef
        myViewHolder. mFoodItemnameView?.text = myOrder.itemname
        if (myOrder.type=="veg"){
            myViewHolder.mFoodItemnamePrice_VNIndicator?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.veg,0,0,0)
            myViewHolder.mFoodItemnamePrice_VNIndicator?.text = myOrder.currency+myOrder.price
        }   else{
            myViewHolder.mFoodItemnamePrice_VNIndicator?.setCompoundDrawablesWithIntrinsicBounds(R.drawable.nonveg,0,0,0)
            myViewHolder.mFoodItemnamePrice_VNIndicator?.text = myOrder.currency+myOrder.price
        }
        ImageLoader.getInstance().displayImage(  Url.IMAGES_BASICPATH_URL+myOrder.image,
        myViewHolder.mFoodItemImageView,options);
    }

    inner class FoodItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var mChefNameView: TextView? = null
        internal var mFoodItemnamePrice_VNIndicator: TextView? = null
        internal var mFoodItemnameView: TextView? = null

        internal var mFoodItemImageView: ImageView? = null
        init {
            mChefNameView = itemView.findViewById(R.id.product_row_checfnameTV)
            mFoodItemnameView = itemView.findViewById(R.id.product_row_fooditemnameTV)
            mFoodItemnamePrice_VNIndicator = itemView.findViewById(R.id.product_priceTV)
            mFoodItemImageView = itemView.findViewById(R.id.product_list_row_productIM)

        }


    }



}