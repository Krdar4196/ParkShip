package ecccomp.team_create4.parkship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    lateinit var navigation: BottomNavigationView
    val database:DatabaseReference = Firebase.database.reference
    val Account_Bundle: Bundle = Bundle()
    val acRef: DatabaseReference = Firebase.database.getReference("ksj:Dataset")
    val a = acRef.child("usr:Account").child("0").child("id").get()

    lateinit var fragment: Fragment

    lateinit var acid: String
    lateinit var rpcount: String

    var AC_Bundle: Bundle = Bundle()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            //マップ画面
            R.id.menu1 -> {
                fragment = map().apply { arguments = AC_Bundle }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //検索画面
            R.id.menu2 -> {
                fragment = SearchFragment().apply { arguments = AC_Bundle }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //マイページ画面
            R.id.menu3 -> {
                fragment = MyPageFragment().apply { arguments = AC_Bundle }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //フレンド画面
            R.id.menu4 -> {
                fragment = FriendFragment().apply { arguments = AC_Bundle }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //設定画面
            R.id.menu5 -> {
                fragment = SettingFragment().apply { arguments = AC_Bundle }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment)
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        return@OnNavigationItemSelectedListener false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** アカウントを追加するコード
        val acRef: DatabaseReference = Firebase.database.getReference("ksj:Dataset")
        acRef.child("usr:Account").child("0").child("id").setValue("1")
        acRef.child("usr:Account").child("0").child("rpcount").setValue("0")
        acRef.child("usr:Account").child("0").child("name").setValue("井石太郎")
        acRef.child("usr:Account").child("0").child("pass").setValue("123qwecc")
        acRef.child("usr:Account").child("0").child("friend").child("0").setValue("1") **/

        acRef.child("usr:Account").child("0").child("id").get()
            .addOnSuccessListener { id ->
                acRef.child("usr:Account").child("0").child("rpcount").get()
                    .addOnSuccessListener { count ->

                        acid = id.value.toString()
                        rpcount = count.value.toString()

                        AC_Bundle.putString("id", acid)
                        AC_Bundle.putString("rpcount", rpcount)

                        navigation = findViewById(R.id.bottom_nav_bar)
                        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

                        fragment = map()

                        fragment.setArguments(AC_Bundle)

                        val bundle = Bundle().apply {
                            putString("id", acid)
                            putString("rpcount", rpcount)
                        }

                        fragment = map().apply { arguments = bundle }

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit()
                    }
            }
    }
}