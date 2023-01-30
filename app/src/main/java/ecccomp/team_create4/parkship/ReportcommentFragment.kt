package ecccomp.team_create4.parkship

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass.
 * Use the [ReportcommentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReportcommentFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reportcomment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val reportcommentNametext : TextView = view.findViewById(R.id.reportcommentparknameTextview)
        val reportcommentReportbutton : Button = view.findViewById(R.id.reportcommentReportbutton)
        val reportEdit : EditText = view.findViewById(R.id.reportcommentReportcontentEdittext)

        val parkreportcommentbundle = arguments
        var account:Int? = 0
        var rpcount:Int? = 0
        var parkid:Int? = 0
        var parkname:String? = null
        var parkcount:String? = null

        if (parkreportcommentbundle != null){
            account = parkreportcommentbundle.getString("account")?.toInt()
            rpcount = parkreportcommentbundle.getString("rpcount")?.toInt()
            parkid = parkreportcommentbundle.getString("id")?.toInt()
            parkname = parkreportcommentbundle.getString("name")
            parkcount = parkreportcommentbundle.getString("count")

            reportcommentNametext.setText(parkname)
            Toast.makeText(requireActivity(), "${parkid!!-1}", Toast.LENGTH_LONG).show()
        }

        reportcommentReportbutton.setOnClickListener {
            val database = Firebase.database.reference
            val dpRef: DatabaseReference = Firebase.database.getReference("ksj:Dataset/")
            val rpRef: DatabaseReference = Firebase.database.getReference("ksj:Dataset/ksj:Park/${parkid!!-1}")
            val acRef: DatabaseReference = Firebase.database.getReference("ksj:Dataset/usr:Account/${account!!-1}")

            val count = parkcount?.toInt()

            //公園に通報情報を追加する
            rpRef.child("count").setValue("${count?.plus(1)}")
            rpRef.child("elm:rpt").child("$parkcount").child("account_id").setValue("${account}")
            rpRef.child("elm:rpt").child("$parkcount").child("comment").setValue("${reportEdit.text}")

            //アカウントに通報情報を追加する
            acRef.child("report").child("${rpcount}").child("park_id").setValue("$parkid")
            acRef.child("rpcount").setValue("${rpcount?.plus(1)}")

            Log.d("detail", "${reportEdit.text}")
            val toast = Toast.makeText(requireActivity(), "送信しました。${reportEdit.text}", Toast.LENGTH_LONG)

            reportEdit.setText("")
            toast.show()

            reportcommentReportbutton.setEnabled(false)
            reportEdit.setEnabled(false)
            //activity?.supportFragmentManager?.popBackStack()
        }
    }
}