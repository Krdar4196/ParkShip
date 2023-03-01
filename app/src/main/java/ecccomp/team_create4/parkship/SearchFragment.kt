package ecccomp.team_create4.parkship

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner

class SearchFragment : Fragment() {

    //MainActivityからユーザ情報を取得するデータ
    lateinit var Account_ID: String
    lateinit var Account_RP: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().setTitle("公園検索")

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
        val screen1 = inflater.inflate(R.layout.fragment_search, container, false)

        // Spinnerの取得
        val searchprefecturesSpinner:Spinner = screen1.findViewById(R.id.searchprefecturesSpinner)
        val searchcitysSpinner:Spinner = screen1.findViewById(R.id.searchcitysSpinner)

        // Adapterの生成
        val searchprefecturesadapter = ArrayAdapter.createFromResource(requireActivity(), R.array.spinnerItems, android.R.layout.simple_spinner_item)
        val searchcitysadapter = ArrayAdapter.createFromResource(requireActivity(), R.array.spinnerItems, android.R.layout.simple_spinner_item)

        // 選択肢の各項目のレイアウト
        searchprefecturesadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        searchcitysadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // AdapterをSpinnerのAdapterとして設定
        searchprefecturesSpinner.adapter = searchprefecturesadapter
        searchcitysSpinner.adapter = searchprefecturesadapter
        searchcitysSpinner.isClickable = false

        return screen1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}