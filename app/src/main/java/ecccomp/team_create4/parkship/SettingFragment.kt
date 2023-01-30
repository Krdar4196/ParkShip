package ecccomp.team_create4.parkship

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

private const val SETTING_LIST_TAG = "Setting_List_Tag"

class SettingFragment : Fragment() {

    //MainActivityからユーザ情報を取得するデータ
    lateinit var Account_ID: String
    lateinit var Account_RP: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setTitle("設定")

        Account_ID = arguments?.getString("id").toString()
        Account_RP = arguments?.getString("rpcount").toString()
        Log.d("account", "account id : $Account_ID")
        Log.d("account", "account rp : $Account_RP")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val screen1 = inflater.inflate(R.layout.fragment_setting, container, false)

        val settingListview: ListView = screen1.findViewById(R.id.settingListview)

        val arrayTitle = arrayOf("フレンドの通報ピンのみ表示","通報ピンをフレンドのみに表示","利用規約")

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            arrayTitle
        )
        settingListview.adapter = adapter

        return screen1
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}