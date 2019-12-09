package com.anagha.dabbamen

import adapters.AddressItemsListAdapter
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apirelated.CallApi
import apirelated.Url
import apirelated.addresses_related_models.Addresses_List
import apirelated.addresses_related_models.Data
import kotlinx.android.synthetic.main.fragment_explore.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utilities.NetworkConnectionCheck
import utilities.WebCall
import java.util.*
import java.util.concurrent.TimeUnit


class AddressesList : AppCompatActivity(), View.OnClickListener {
    var _context: Context = this@AddressesList
    lateinit var mListRecyclerView: RecyclerView
    lateinit var mToolbar: Toolbar;
    var mAddressitemsListAdapter: AddressItemsListAdapter? = null
    lateinit var mlinkAddAddressTV: TextView
    override fun onClick(v: View?) {
        Toast.makeText(
            _context,
            "Add New Address Clicked",
            Toast.LENGTH_SHORT
        ).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_delivery_addresses_list)
        mToolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(mToolbar);
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        val actionbar = supportActionBar
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        mToolbar.title="Address List"
        mToolbar.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })
        initUI()
        uiListener()
    }

    private fun uiListener() {
        mlinkAddAddressTV.setOnClickListener(this)
    }

    @SuppressLint("NewApi")
    private fun initUI() {
        //toolbar = findViewById<Toolbar>(R.id.toolbar)
        this.mlinkAddAddressTV = findViewById<TextView>(R.id.addAddressTV)
        mListRecyclerView =
            findViewById<RecyclerView>(R.id.selectdeliveryaddresses_list_recycler_view)
    }

    override fun onStart() {
        super.onStart()

        mListRecyclerView.setLayoutManager(LinearLayoutManager(_context))
        mListRecyclerView.setHasFixedSize(true)
        if (_context?.let { NetworkConnectionCheck.checkInternetConnection(it) }!!) {
            addresseslistGet();
        } else {
            _context?.let {
                _context?.getString(com.anagha.dabbamen.R.string.internet_enable)?.let { it1 ->
                    WebCall(it).DialogForWifi_Enable_CloseDialog(
                        it1,
                        _context!!.getString(com.anagha.dabbamen.R.string.internet_enable_message),
                        R.drawable.warning_red
                    )
                }
            };
        }


    }

    private fun addresseslistGet() {
        //defining a progress dialog to show while signing up
        val progressDialog = ProgressDialog(_context)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
        var alMyAndroidOS = ArrayList<Data>()
        alMyAndroidOS.clear()
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        //building retrofit object
        val retrofit = Retrofit.Builder()
            .baseUrl(Url.BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Defining retrofit api service
        val service = retrofit.create(CallApi::class.java!!)
        //defining the call
        val call = service.addresseslistGet()
        //calling the api
        call.enqueue(object : Callback<Addresses_List> {
            override fun onResponse(
                call: Call<Addresses_List>,
                response: Response<Addresses_List>
            ) {
                //hiding progress dialog
                progressDialog.dismiss()
                val itemsData = response.body()
                if (itemsData != null) {
                    alMyAndroidOS.addAll(itemsData.data!!)
                    if (alMyAndroidOS.size > 0) {
                        emptyTV.visibility = View.GONE
                        mListRecyclerView.visibility = View.VISIBLE
                        mAddressitemsListAdapter = AddressItemsListAdapter(alMyAndroidOS);
                        mListRecyclerView.adapter = AddressItemsListAdapter(alMyAndroidOS)
                        mListRecyclerView.setAdapter(mAddressitemsListAdapter);
                    } else {
                        emptyTV.visibility = View.VISIBLE
                        mListRecyclerView.visibility = View.GONE
                        emptyTV.setText("No Items List Data Found")
                    }
                }
            }

            override fun onFailure(call: Call<Addresses_List>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(
                    _context!!.applicationContext,
                    "error :" + t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }


    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            R.anim.activity_animation_right_to_left,
            R.anim.right_to_left
        );
    }
}