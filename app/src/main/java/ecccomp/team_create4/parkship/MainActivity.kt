package ecccomp.team_create4.parkship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
    lateinit var acid: String

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            //マップ画面
            R.id.menu1 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, map())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //マイページ画面
            R.id.menu2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, SearchFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //フレンド画面
            R.id.menu3 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, FriendFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //設定画面
            R.id.menu4 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, MyPageFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //設定画面
            R.id.menu5 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, SettingFragment())
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

        navigation = findViewById(R.id.bottom_nav_bar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        var fragment = map()

        val acdata = database.child("ksj:Dataset").child("usr:Account").child("0")

        acdata.child("name").get()
            .addOnSuccessListener {
                Log.d("account", "${it.value}")
            }

        acdata.child("id").get()
            .addOnSuccessListener { id ->
                Log.d("account", "${id.value}")
                val acid = id.value as String

                acdata.child("rpcount").get()
                    .addOnSuccessListener {
                        val bundle = Bundle().apply {
                            putString("id", id.value as String)
                            putString("rpcount", it.value as String)
                        }

                        fragment = map().apply { arguments = bundle }

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frameLayout, fragment)
                            .commit()
                    }

            }

    }
}