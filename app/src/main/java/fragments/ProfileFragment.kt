package fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.anagha.dabbamen.AddressesList
import com.anagha.dabbamen.R
import userdemoscreens.ForgotPassword

class ProfileFragment : Fragment() {
    val language = arrayOf(
        "Edit Profile",
        "Saved Addresses",
        "Order History",
        "Call Support",
        "Email Support",
        "Logout"
    )
    lateinit var listView: ListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile, container, false)
        listView = view.findViewById<ListView>(R.id.listView)
        var colorArrays = resources.getStringArray(R.array.profile_list_array)
        var arrayAdapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, colorArrays)
        listView.adapter = arrayAdapter
        return view
    }


    override fun onStart() {
        super.onStart()
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val selectedItem = adapterView.getItemAtPosition(position) as String
                val itemIdAtPos = adapterView.getItemIdAtPosition(position)
                when (position) {
                    0 -> Toast.makeText(
                        context,
                        "click item $selectedItem item position $itemIdAtPos",
                        Toast.LENGTH_SHORT
                    ).show()
                    1 ->{
                        val intent = Intent(context, AddressesList::class.java)
                        startActivity(intent)
                        /*getActivity()?.overridePendingTransition(
                            R.anim.activity_animation_right_to_left,
                            R.anim.right_to_left
                        );*/

                        Toast.makeText(
                        context,
                        "click item $selectedItem item position $itemIdAtPos",
                        Toast.LENGTH_SHORT
                    ).show()
                    }

                    2 -> Toast.makeText(
                        context,
                        "click item $selectedItem item position $itemIdAtPos",
                        Toast.LENGTH_SHORT
                    ).show()
                    3 -> {
                        val callIntent = Intent(Intent.ACTION_DIAL)
                        callIntent.data = Uri.parse("tel:1234567890" )
                        startActivity(callIntent)

                        /*Toast.makeText(
                            context,
                            "click item $selectedItem item position $itemIdAtPos",
                            Toast.LENGTH_SHORT
                        ).show()*/
                    }
                    4 -> {
                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto:") // only email apps should handle this
                        intent.putExtra(Intent.EXTRA_EMAIL, "emailsupport@mailinator.com")
                        intent.putExtra(Intent.EXTRA_SUBJECT,"Email Support")
                        if (intent.resolveActivity(activity?.packageManager) != null) {
                            startActivity(intent)
                        }

                       /* Toast.makeText(
                            context,
                            "click item $selectedItem item position $itemIdAtPos",
                            Toast.LENGTH_SHORT
                        ).show()*/
                    }
                    5 -> Toast.makeText(
                        context,
                        "click item $selectedItem item position $itemIdAtPos",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}// Required empty public constructor