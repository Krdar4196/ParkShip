package ecccomp.team_create4.parkship

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ReportFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ParkDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ParkDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_park_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        /*
        super.onViewCreated(view, savedInstanceState)
        val parkdetailNametext: TextView = view.findViewById(R.id.parkdetailNametext)
        val parkdetailAddresstext: TextView = view.findViewById(R.id.parkdetailAddresstext)
        val parkdetailReporttext: TextView = view.findViewById(R.id.parkdetailReporttext)
        val parkdetailReportbutton: Button = view.findViewById(R.id.parkdetailReportbutton)

        //ここからbundle
        val reportCount = 1//送信内容
        val bundle = Bundle()
        val fragment = ReportcommentFragment()
        bundle.putString("BUNDLE_REPORT_COUNT",reportCount.toString())//送信内容入力
        fragment.arguments = bundle
        parkdetailReporttext.setText("通報回数:" + reportCount + "回")
        // ボタンのリスナーを作成
        parkdetailReportbutton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.container,fragment)//送信内容付加
                .commit()
        }
        //ここまでbundle
        */

        super.onViewCreated(view, savedInstanceState)
        val parkdetailNametext: TextView = view.findViewById(R.id.parkdetailNametext)
        val parkdetailAddresstext: TextView = view.findViewById(R.id.parkdetailAddresstext)
        val parkdetailReporttext: TextView = view.findViewById(R.id.parkdetailReporttext)
        /*bundle実験
        val reportCount = 1
        val bundle = Bundle()
        val fragment = ReportcommentFragment()
        bundle.putString("BUNDLE_REPORT_COUNT",reportCount.toString())
        fragment.arguments = bundle//実験
        parkdetailReporttext.setText("通報回数:" + reportCount + "回")
         */
        val parkdetailReportbutton: Button = view.findViewById(R.id.parkdetailReportbutton)
        // ボタンのリスナーを作成
        parkdetailReportbutton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container,ReportcommentFragment())
                //.add(R.id.container,fragment)//実験
                .commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ParkDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ParkDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}