package cl.freedom.digitalfactoryapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cl.freedom.digitalfactoryapp.R
import cl.freedom.digitalfactoryapp.retrofit.AppClient
import cl.freedom.digitalfactoryapp.retrofit.response.Indicator
import cl.freedom.digitalfactoryapp.retrofit.response.ResponseIndicatorsResume
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MyIndicatorRecyclerViewAdapter(activity)
        initializeIndicators()
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

    fun initializeIndicators() {
        val appClient: AppClient? = AppClient.instance
        val appService = appClient?.appService
        val call = appService?.allIndicatorResume
        call!!.enqueue(object : Callback<ResponseIndicatorsResume?> {
            override fun onResponse(call: Call<ResponseIndicatorsResume?>, response: Response<ResponseIndicatorsResume?>) {
                if (response.isSuccessful) {
                    val responseIndicator = response.body()
                    for (indicator in responseIndicator!!.indicators) {
                        Log.d("Indicadores", indicator!!.nombre)
                    }
                    updateAdapter(responseIndicator!!.indicators)
                } else {
                    Toast.makeText(activity, "Algo ha ido mal " + response.code(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseIndicatorsResume?>, t: Throwable) {
                Toast.makeText(activity, "Algo ha ido mal 2", Toast.LENGTH_LONG).show()
            }
        })
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
        }
        return view
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