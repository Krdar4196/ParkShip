package ecccomp.team_create4.parkship

 import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.content.ContentValues
import android.widget.Toast
import ecccomp.team_create4.parkship.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //追記
        binding = ActivityLoginBinding.inflate(layoutInflater)
        //変更
        //setContentView(R.layout.activity_login)
        setContentView(binding.root)

        val flag01 = "Start/開始"
        Log.v(ContentValues.TAG,flag01)

        /*(ボタン)-------------------------------------------------------*/
        binding.loginButton.setOnClickListener {

            //トースト
            val msg = "LOGIN"
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            //Run
            val lo_setOCL  = "LOGIN/ログイン"
            Log.v(ContentValues.TAG,lo_setOCL)

            //ログイン画面を呼び出すIntentを生成
            //val intent = Intent(application, LoginActivity::class.java)
            //Intent呼び出しを実行する
            //startActivity(intent)

        }
        /*-------------------------------------------------------(ボタン)*/

        /*(ログインボタン)-------------------------------------------------------*/
        binding.loginButton.setOnClickListener {

            //トースト
            val msg = "LOGIN"
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            //Run
            val lo_setOCL  = "LOGIN/ログイン"
            Log.v(ContentValues.TAG,lo_setOCL)

            //ログイン画面を呼び出すIntentを生成
            //val intent = Intent(application, LoginActivity::class.java)
            //Intent呼び出しを実行する
            //startActivity(intent)

        }
        /*-------------------------------------------------------(ログインボタン)*/

        /*(ボタン)-------------------------------------------------------*/
        binding.loginButton.setOnClickListener {

            //トースト
            val msg = "LOGIN"
            Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
            //Run
            val lo_setOCL  = "LOGIN/ログイン"
            Log.v(ContentValues.TAG,lo_setOCL)

            //ログイン画面を呼び出すIntentを生成
            //val intent = Intent(application, LoginActivity::class.java)
            //Intent呼び出しを実行する
            //startActivity(intent)

        }
        /*-------------------------------------------------------(ボタン)*/

        val flag02 = "Stand-By/待機"
        Log.v(ContentValues.TAG,flag02)

    }

}