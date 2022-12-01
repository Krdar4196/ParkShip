package ecccomp.team_create4.parkship

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import ecccomp.team_create4.parkship.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    //バインディング読み込み
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //追加
        binding = ActivityLoginBinding.inflate(layoutInflater)
        //変更
        //setContentView(R.layout.activity_login)
        setContentView(binding.root)

        /*(ボタン)-------------------------------------------------------*/
        binding.button01.setOnClickListener {
            //送る
            //ログイン画面を呼び出すIntentを生成
            val intent = Intent(application, LoginActivity::class.java)
            //Intent呼び出しを実行する
            startActivity(intent)
            //トースト
            val msg = "LOGIN"
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()

            /*
            val ve = "VERBOSE/冗長"
            Log.v(ContentValues.TAG,ve)

            val de = "DEBUG/デバック"
            Log.d(ContentValues.TAG,de)

            val inf = "INFORMATION/情報"
            Log.i(ContentValues.TAG,inf)

            val wa = "WARNING/警告"
            Log.w(ContentValues.TAG,wa)

            val er = "ERROR/エラー"
            Log.e(ContentValues.TAG,er)
            */
        }
        /*-------------------------------------------------------(ボタン)*/

    }
}