package com.prashannar.mitosys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //splash screen
        Thread.sleep(3000)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)

        //hide action bar
        supportActionBar?.hide()

        loginBtn.setOnClickListener {
            checkInput()
        }
    }

    private fun checkInput() {
        if(unameET.editText?.text.toString() != "userOne"){
            unameET.error = "Invalid Username"
        }else if(passET.editText?.text.toString() != "Nepal123"){
            passET.error = "Invalid Password"
        }else{
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        supportActionBar?.hide()
        super.onStart()
    }

    override fun onResume() {
        supportActionBar?.hide()
        super.onResume()
    }

    override fun onRestart() {
        supportActionBar?.hide()
        super.onRestart()
    }
}



