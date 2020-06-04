package cl.freedom.digitalfactoryapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.freedom.digitalfactoryapp.R
import cl.freedom.digitalfactoryapp.data.IndicatorViewModel
import cl.freedom.digitalfactoryapp.retrofit.response.Indicator

class IndicatorListFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment() {
    private var recyclerView: RecyclerView? = null
    private var adapter: MyIndicatorRecyclerViewAdapter? = null
    // TODO: Customize parameters
    private var mColumnCount = 1
    private var indicatorViewModel: IndicatorViewModel? = null
    private var indicatorList: MutableList<Indicator?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MyIndicatorRecyclerViewAdapter(activity)
        indicatorViewModel = ViewModelProvider(this).get(IndicatorViewModel::class.java)

        if (arguments != null) {
            mColumnCount = arguments!!.getInt(ARG_COLUMN_COUNT)
        }
    }

    fun filterAdapter(newText: String?) {
        adapter!!.filter.filter(newText)
    }

    fun updateAdapter(indicators: MutableList<Indicator?>?) {
        adapter!!.setData(indicators)
        adapter!!.notifyDataSetChanged()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_indicator_list, container, false)
        // Set the adapter
        if (view is RecyclerView) {
            val context = view.getContext()
            recyclerView = view
            if (mColumnCount <= 1) {
                recyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            } else {
                recyclerView!!.layoutManager = GridLayoutManager(context, mColumnCount)
            }
            recyclerView!!.adapter = adapter
            loadIndicatorData()
        }
        return view
    }

    private fun loadIndicatorData() {
 //       userViewModel!!.getUser(email)!!.observe(this, Observer<User?> { user: User? ->
        indicatorViewModel!!.indicators!!.observe(viewLifecycleOwner, Observer<MutableList<Indicator?>?> {
            indicators : MutableList<Indicator?>? ->
            if(indicators != null)
            {
                indicatorList = indicators
                updateAdapter(indicatorList!!)
            }
        })
    }

    companion object {
        // TODO: Customize parameter argument names
        private const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        fun newInstance(columnCount: Int): IndicatorListFragment {
            val fragment = IndicatorListFragment()
            val args = Bundle()
            args.putInt(ARG_COLUMN_COUNT, columnCount)
            fragment.arguments = args
            return fragment
        }
    }
}