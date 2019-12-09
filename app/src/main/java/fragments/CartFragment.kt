package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anagha.dabbamen.R

class CartFragment : Fragment() {
    // private lateinit var viewPager: ViewPager
    //private lateinit var tabs: TabLayout


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.cartlayout, container, false)
        //viewPager = view.findViewById(R.id.viewpager_main)
        //tabs = view.findViewById(R.id.tabs_main)

        /* val fragmentAdapter = MyPagerAdapter(childFragmentManager)
         viewPager.adapter = fragmentAdapter*/
        // tabs.setupWithViewPager(viewPager)

        return view
    }

}// Required empty public constructor