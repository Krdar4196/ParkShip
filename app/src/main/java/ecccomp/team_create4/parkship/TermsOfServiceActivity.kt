package ecccomp.team_create4.parkship

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.Toast

class TermsOfServiceActivity : AppCompatActivity() {
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        //
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_of_service)
        //
        var BackButton = findViewById<Button>(R.id.BackButton)
        //
        /*(ボタン)-------------------------------------------------------*/
        BackButton.setOnClickListener {
            //送る
            //トースト
            val msg = "Back"
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
            //Run
            val su_setOCL  = "BACK/戻る"
            Log.v(ContentValues.TAG,su_setOCL)
            // 画面を閉じる
            finish()
        }
        /*-------------------------------------------------------(ボタン)*/
    }
}