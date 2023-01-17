package ecccomp.team_create4.parkship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}