package ecccomp.team_create4.parkship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navigation: BottomNavigationView

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

        navigation = findViewById(R.id.bottom_nav_bar)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }
}