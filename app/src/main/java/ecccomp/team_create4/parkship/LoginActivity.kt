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
    }

}