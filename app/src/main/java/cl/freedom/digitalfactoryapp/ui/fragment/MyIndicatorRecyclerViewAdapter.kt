package cl.freedom.digitalfactoryapp.ui.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cl.freedom.digitalfactoryapp.OnItemClickListener
import cl.freedom.digitalfactoryapp.R
import cl.freedom.digitalfactoryapp.retrofit.response.Indicator
import cl.freedom.digitalfactoryapp.ui.DashboardActivity
import java.util.*

class MyIndicatorRecyclerViewAdapter(private val ctx: Context?) : RecyclerView.Adapter<MyIndicatorRecyclerViewAdapter.ViewHolder>(), Filterable {
    private var mValues: MutableList<Indicator?>? = null
    private var mValuesFilter: List<Indicator?>? = null
    private var mListener: OnItemClickListener? = null
    fun setData(items: MutableList<Indicator?>?) {
        mValues = items
        mValuesFilter = ArrayList(mValues!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_indicator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvIndicator.text = mValues!!.get(position)!!.nombre
        holder.tvValue.text = mValues!!.get(position)!!.valor.toString()
        holder.mView.setOnClickListener { v ->
            mListener = holder
            (mListener as ViewHolder).onItemClick(v, position)
        }
    }

    override fun getItemCount(): Int {
        return if (mValues == null) 0 else mValues!!.size
    }

    override fun getFilter(): Filter {
        return filter
    }

    private val filter: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Indicator?> = ArrayList()
            if (constraint == null || constraint.length == 0) {
                filteredList.addAll(mValuesFilter!!)
            } else {
                val filterPattern = constraint.toString().toLowerCase().trim { it <= ' ' }
                for (indicator in mValuesFilter!!) {
                    if (indicator!!.codigo!!.toLowerCase().contains(filterPattern)) {
                        filteredList.add(indicator)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            mValues!!.clear()
            mValues!!.addAll(results.values as Collection<Indicator?>)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView), OnItemClickListener {
        var tvIndicator: TextView
        var tvValue: TextView
        override fun toString(): String {
            return super.toString() + " '" + tvIndicator.text + "'"
        }

        override fun onItemClick(view: View, position: Int) {
            showDetailDialog(view, position)
        }

        private fun showDetailDialog(view: View, position: Int) {
            val fm = (ctx as DashboardActivity?)!!.supportFragmentManager
            var text = "Nombre: " + mValues!!.get(position)!!.nombre
            text += "\nCódigo: " + mValues!!.get(position)!!.codigo
            text += "\nFecha: " + mValues!!.get(position)!!.fecha
            text += "\nUnidad: " + mValues!!.get(position)!!.unidadMedida
            text += "\nValor: " + mValues!!.get(position)!!.valor
            val dialogFragment: MyDialogFragment = MyDialogFragment.Companion.newInstance(text)
            dialogFragment.show(fm, "fragment_dialog")
        }

        init {
            tvValue = mView.findViewById(R.id.text_valor)
            tvIndicator = mView.findViewById(R.id.text_nombre_)
        }
    }

}