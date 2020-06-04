package cl.freedom.digitalfactoryapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import cl.freedom.digitalfactoryapp.R
import org.w3c.dom.Text

class MyDialogFragment : DialogFragment() {
    private var textView: TextView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_indicator, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById(R.id.text_indicators) as TextView
        val text = arguments!!.getString("text", "Enter Name")
        //getDialog().setTitle(title);
        textView.setText(text)
    }

    companion object {
        fun newInstance(text: String?): MyDialogFragment {
            val frag = MyDialogFragment()
            val args = Bundle()
            args.putString("text", text)
            frag.arguments = args
            return frag
        }
    }
}