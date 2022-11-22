package ecccomp.team_create4.parkship

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navigation: BottomNavigationView

    /**
     * 下部ナビゲーションのアイコンを選択した時の処理
     * マップ      : 公園の場所が分かる Google Map フラグメント
     * 検索        : 公園の検索ができるフラグメント
     * マイページ   : ログインユーザーの情報が表示されるフラグメント
     * 設定        : 各設定が行えるフラグメント
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            //マップ
            R.id.menu1 -> {
                /** エラー起こるのでコメントアウトしてます **/
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, map())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //検索
            R.id.menu2 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, SearchFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //マイページ
            R.id.menu3 -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, MyPageFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            //設定
            R.id.menu4 -> {
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