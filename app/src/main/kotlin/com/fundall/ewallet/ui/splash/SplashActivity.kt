package com.fundall.ewallet.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fundall.ewallet.R
import com.fundall.ewallet.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        start_button.setOnClickListener {
            //navigate to main activity
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}
