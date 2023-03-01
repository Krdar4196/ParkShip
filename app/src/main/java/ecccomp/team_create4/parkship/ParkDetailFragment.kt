package ecccomp.team_create4.parkship

import android.os.Bundle
import android.text.TextUtils.replace
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ReportFragment

/**
 * A simple [Fragment] subclass.
 * Use the [ParkDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ParkDetailFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_park_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val parkdetailNametext: TextView = view.findViewById(R.id.parkdetailNametext)
        val parkdetailAddresstext: TextView = view.findViewById(R.id.parkdetailAddresstext)
        //val parkdetailReporttext: TextView = view.findViewById(R.id.parkdetailReporttext)
        val parkdetailReportbutton: Button = view.findViewById(R.id.parkdetailReportbutton)

        val parkdetailbundle = arguments
        var account:String? = null
        var rpcount:String? = null
        var parkid:String? = null
        var parkname:String? = null
        var parkaddress:String? = null
        var parkReportcount:String? = null

        if (parkdetailbundle != null){
            account = parkdetailbundle.getString("account")
            rpcount = parkdetailbundle.getString("rpcount")
            parkid = parkdetailbundle.getString("id")
            parkname = parkdetailbundle.getString("name")
            parkaddress = parkdetailbundle.getString("address")
            parkReportcount = parkdetailbundle.getString("count")

            Log.d("account", "$parkid, $parkname")


            parkdetailNametext.setText(parkname)
            parkdetailAddresstext.setText("住所:" + parkaddress)
            //parkdetailReporttext.setText("通報件数:" + parkReportcount + "件")
        }
        // ボタンのリスナーを作成
        parkdetailReportbutton.setOnClickListener {
            var fragment = ReportcommentFragment()
            var Park_Bundle:Bundle = Bundle()

            Log.d("detail", "report detail : $parkid, $parkname")

            Park_Bundle.putString("account", account)
            Park_Bundle.putString("rpcount", rpcount)
            Park_Bundle.putString("id", parkid.toString())
            Park_Bundle.putString("name", parkname)
            Park_Bundle.putString("count", parkReportcount)

            fragment.setArguments(Park_Bundle)

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()

        }
    }
}