package ecccomp.team_create4.parkship

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MyPageFragment : Fragment() {

    //MainActivityからユーザ情報を取得するデータ
    lateinit var Account_ID: String
    lateinit var Account_RP: String
    lateinit var Account_Name: String

    private lateinit var database: DatabaseReference

    lateinit var acRef: DatabaseReference
    lateinit var pkRef: DatabaseReference

    private lateinit var recyclerView: RecyclerView

    var Report_ID: ArrayList<String> = ArrayList()
    var Park_Name: ArrayList<String> = ArrayList()
    var Comment: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Account_ID = arguments?.getString("id").toString()
        Account_RP = arguments?.getString("rpcount").toString()
        Log.d("account", "account id : $Account_ID")
        Log.d("account", "account rp : $Account_RP")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val icon_name: TextView = view.findViewById(R.id.icon_name)

        database = Firebase.database.reference
        acRef = Firebase.database.getReference("ksj:Dataset/usr:Account/${Account_ID.toInt()-1}")
        pkRef = Firebase.database.getReference("ksj:Dataset/ksj:Park")
        acRef.child("name").get()
            .addOnSuccessListener {
                icon_name.setText(it.value.toString())
            }.addOnCanceledListener {
                icon_name.setText("Guest_4196")
            }

        var items: MutableList<ClipData.Item> = mutableListOf()

        for (i in 0..Account_RP.toInt()-1){
            acRef.child("report").child("$i").child("park_id").get()
                .addOnSuccessListener { pid ->
                acRef.child("report").child("$i").child("comment_id").get()
                    .addOnSuccessListener { cid ->
                        Log.d("account", "${pid.value}")
                        pkRef.child("${Integer.parseInt(pid.value.toString())-1}").child("ksj:nop").get()
                            .addOnSuccessListener { pname ->
                                pkRef.child("${Integer.parseInt(pid.value.toString())-1}").child("elm:rpt")
                                    .child("${Integer.parseInt(cid.value.toString())-1}").child("comment").get()
                                    .addOnSuccessListener { comt ->
                                        Park_Name.add(pname.value.toString())
                                        Comment.add(comt.value.toString())
                                        items.add(ClipData.Item("公園名 : ${Park_Name[i]}", "コメント : ${Comment[i]}"))

                                        if (i == Account_RP.toInt()-1){
                                            recyclerView = view.findViewById(R.id.report_histories)
                                            recyclerView.adapter = MyPageRecyclerAdapter(items)
                                            recyclerView.layoutManager = LinearLayoutManager(activity)
                                        }
                                    }
                            }
                    }
                }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        requireActivity().title = "マイページ"
    }
}