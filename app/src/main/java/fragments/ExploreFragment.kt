package fragments

import adapters.FoodItemsListAdapter
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import apirelated.CallApi
import apirelated.Url
import apirelated.items_model.Data
import apirelated.items_model.FoodItemsAPIResponse
import com.anagha.dabbamen.R
import kotlinx.android.synthetic.main.fragment_explore.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import utilities.NetworkConnectionCheck
import utilities.WebCall
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import androidx.recyclerview.widget.DividerItemDecoration




class ExploreFragment : Fragment() {
    var veg_All_Switch: String = "all"
    var foodTypeRB: String = "Lunch"
    lateinit var currentDayRB: String
    //lateinit var veg_all_switchBTv: Switch
    lateinit var mListRecyclerView: RecyclerView
    var mfooditemsListAdapter: FoodItemsListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_explore, container, false)
        //val daySelection = view.findViewById(R.id.dayselectRG) as SegmentedGroup
        mListRecyclerView = view.findViewById<RecyclerView>(R.id.itemsRecyclerview)
        return view
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        lunch_dinnerRG.setOnCheckedChangeListener(clickListener)
        veg_nonvegRG.setOnCheckedChangeListener(clickListener)
        dayselectRG.setOnCheckedChangeListener(clickListener)
        mListRecyclerView.setLayoutManager(LinearLayoutManager(getActivity()))
        mListRecyclerView.setHasFixedSize(true)
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek = sdf.format(d)

        currentDayRB = when (dayOfTheWeek) {
            "Monday" -> "Tuesday"
            "Tuesday" -> "Wednesday"
            "Wednesday" -> "Thursday"
            "Thursady" -> "Friday"
            "Friday" -> "Monday"
            "Saturday" -> "Monday"
            else -> "Monday"
        }

        if (dayOfTheWeek.equals("Monday")) {
            radioOne.setText("Tu")
            radioTwo.setText("We")
            radioThree.setText("Th")
            radioFour.setText("Fr")
            radioFive.setText("Mo")
        } else if (dayOfTheWeek.equals("Tuesday")) {
            radioOne.setText("We")
            radioTwo.setText("Th")
            radioThree.setText("Fr")
            radioFour.setText("Mo")
            radioFive.setText("Tu")
        } else if (dayOfTheWeek.equals("Wednesday")) {
            radioOne.setText("Th")
            radioTwo.setText("Fr")
            radioThree.setText("Mo")
            radioFour.setText("Tu")
            radioFive.setText("We")
        } else if (dayOfTheWeek.equals("Thursday")) {
            radioOne.setText("Fr")
            radioTwo.setText("Mo")
            radioThree.setText("Tu")
            radioFour.setText("We")
            radioFive.setText("Th")
        } else {
            radioOne.setText("Mo")
            radioTwo.setText("Tu")
            radioThree.setText("We")
            radioFour.setText("Th")
            radioFive.setText("Fr")
        }
        if (getContext()?.let { NetworkConnectionCheck.checkInternetConnection(it) }!!) {
            getFoodItemsList(
                foodTypeRB,
                veg_All_Switch,
                "17.387140",
                "78.491684",
                currentDayRB,
                "Indian"
            );
        } else {
            getContext()?.let {
                getContext()?.getString(com.anagha.dabbamen.R.string.internet_enable)?.let { it1 ->
                    WebCall(it).DialogForWifi_Enable_CloseDialog(
                        it1,
                        getContext()!!.getString(com.anagha.dabbamen.R.string.internet_enable_message),
                        R.drawable.warning_red
                    )
                }
            };
        }

    }

    val clickListener = RadioGroup.OnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->

        when (radioGroup.getId()) {
            R.id.lunch_dinnerRG -> lunchDinnerFunction(radioGroup)
            R.id.veg_nonvegRG -> vegNonVegSelectFunction(radioGroup)
            R.id.dayselectRG -> daySelectFunction(radioGroup)
        }
    }

    private fun vegNonVegSelectFunction(group: RadioGroup) {
        val checkedRadioButton =
            group?.findViewById<RadioButton>(group.checkedRadioButtonId) as? RadioButton
        checkedRadioButton?.let {
            veg_All_Switch = if (checkedRadioButton?.text.equals("All")) "all" else "Veg"

            if (getContext()?.let { NetworkConnectionCheck.checkInternetConnection(it) }!!) {
                getFoodItemsList(
                    foodTypeRB,
                    veg_All_Switch,
                    "17.387140",
                    "78.491684",
                    currentDayRB,
                    "Indian"
                );
            } else {
                getContext()?.let {
                    getContext()?.getString(R.string.internet_enable)?.let { it1 ->
                        WebCall(it).DialogForWifi_Enable_CloseDialog(
                            it1,
                            getContext()!!.getString(R.string.internet_enable_message),
                            R.drawable.warning_red
                        )
                    }
                };
            }
        }



    }

    private fun lunchDinnerFunction(group: RadioGroup) {
        val checkedRadioButton = group?.findViewById(group.checkedRadioButtonId) as? RadioButton
        checkedRadioButton?.let {
            foodTypeRB = "${checkedRadioButton?.text}"
            if (getContext()?.let { NetworkConnectionCheck.checkInternetConnection(it) }!!) {
                getFoodItemsList(
                    foodTypeRB,
                    veg_All_Switch,
                    "17.387140",
                    "78.491684",
                    currentDayRB,
                    "Indian"
                );
            } else {
                getContext()?.let {
                    getContext()?.getString(R.string.internet_enable)?.let { it1 ->
                        WebCall(it).DialogForWifi_Enable_CloseDialog(
                            it1,
                            getContext()!!.getString(R.string.internet_enable_message),
                            R.drawable.warning_red
                        )
                    }
                };
            }
        }
    }

    private fun daySelectFunction(group: RadioGroup) {
        val checkedRadioButton = group?.findViewById(group.checkedRadioButtonId) as? RadioButton
        checkedRadioButton?.let {
            // currentDayRB = "${checkedRadioButton?.text}"
            currentDayRB = when (checkedRadioButton?.text) {
                "Mo" -> "Monday"
                "Tu" -> "Tuesday"
                "We" -> "Wednesday"
                "Th" -> "Thursady"
                "Fr" -> "Friday"
                "Sa" -> "Saturday"
                else -> "Su"
            }

            Toast.makeText(
                activity,
                "$currentDayRB",
                Toast.LENGTH_LONG
            ).show()
            if (getContext()?.let { NetworkConnectionCheck.checkInternetConnection(it) }!!) {
                getFoodItemsList(
                    foodTypeRB,
                    veg_All_Switch,
                    "17.387140",
                    "78.491684",
                    currentDayRB,
                    "Indian"
                );
            } else {
                getContext()?.let {
                    getContext()?.getString(R.string.internet_enable)?.let { it1 ->
                        WebCall(it).DialogForWifi_Enable_CloseDialog(
                            it1,
                            getContext()!!.getString(R.string.internet_enable_message),
                            R.drawable.warning_red
                        )
                    }
                };
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (getContext()?.let { NetworkConnectionCheck.checkInternetConnection(it) }!!) {
            getFoodItemsList(
                foodTypeRB,
                veg_All_Switch,
                "17.387140",
                "78.491684",
                currentDayRB,
                "Indian"
            );
        } else {
            getContext()?.let {
                getContext()?.getString(com.anagha.dabbamen.R.string.internet_enable)?.let { it1 ->
                    WebCall(it).DialogForWifi_Enable_CloseDialog(
                        it1,
                        getContext()!!.getString(com.anagha.dabbamen.R.string.internet_enable_message),
                        R.drawable.warning_red
                    )
                }
            };
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    private fun getFoodItemsList(
        lunch: String,
        veg: String,
        lat: String,
        long: String,
        day: String,
        lunchtype: String
    ) {
        //defining a progress dialog to show while signing up
        val progressDialog = ProgressDialog(context)
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
        val call = service.getItemsListPost(
            lunch, veg, lat, long, day, lunchtype
        )
        //calling the api
        call.enqueue(object : Callback<FoodItemsAPIResponse> {
            override fun onResponse(
                call: Call<FoodItemsAPIResponse>,
                response: Response<FoodItemsAPIResponse>
            ) {
                //hiding progress dialog
                progressDialog.dismiss()
                val itemsData = response.body()
                if (itemsData != null) {
                    alMyAndroidOS.addAll(itemsData.data!!)
                    if (alMyAndroidOS.size > 0) {
                        emptyTV.visibility = View.GONE
                        mListRecyclerView.visibility = View.VISIBLE
                        mfooditemsListAdapter = FoodItemsListAdapter(alMyAndroidOS);
                        mListRecyclerView.adapter = FoodItemsListAdapter(alMyAndroidOS)
                        mListRecyclerView.setAdapter(mfooditemsListAdapter);
                    } else {
                        emptyTV.visibility = View.VISIBLE
                        mListRecyclerView.visibility = View.GONE
                        emptyTV.setText("No Items List Data Found")
                    }
                }
            }

            override fun onFailure(call: Call<FoodItemsAPIResponse>, t: Throwable) {
                progressDialog.dismiss()
                Toast.makeText(
                    context!!.applicationContext,
                    "error :" + t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }

}


