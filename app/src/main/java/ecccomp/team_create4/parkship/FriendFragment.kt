package ecccomp.team_create4.parkship

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class FriendFragment : Fragment() {

    //MainActivityからユーザ情報を取得するデータ
    lateinit var Account_ID: String
    lateinit var Account_RP: String

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
        return inflater.inflate(R.layout.fragment_friend, container, false)
    }
}