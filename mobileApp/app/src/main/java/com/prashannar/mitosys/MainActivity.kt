package com.prashannar.mitosys

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var sharedpreferences: SharedPreferences? = null
    private var autoSave = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //splash screen
        Thread.sleep(3000)
        val splashScreen = installSplashScreen()
        setContentView(R.layout.activity_main)

        //hide action bar
        supportActionBar?.hide()
        sharedpreferences = getSharedPreferences("autoLogin", Context.MODE_PRIVATE)
        var j = sharedpreferences?.getInt("key", 0)

        if (j != null) {
            if(j > 0){
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }
        }

        loginBtn.setOnClickListener {
            autoSave = 1
            val editor = sharedpreferences!!.edit()
            editor.putInt("key", autoSave)
            editor.apply()
            checkInput()
        }
    }

    private fun checkInput() {
        if (unameET.editText?.text?.trim().toString() != "userOne") {
            unameET.error = "Invalid Username"
        } else if (passET.editText?.text?.trim().toString() != "Nepal123") {
            passET.error = "Invalid Password"
        } else {
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



