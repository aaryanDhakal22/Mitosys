package com.prashannar.mitosys

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_home_screen.*
import retrofit2.HttpException
import java.io.IOException


class HomeScreen : AppCompatActivity() {
    var sharedPreferences: SharedPreferences? = null

    //notification
    val Channel_ID = "channelID"
    val Channel_Name = "channelName"
    val notification_ID = 0

    companion object {
        const val github = "https://github.com/PrashannaR"
    }

    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        //slide menu
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item1 -> {
                    val intent = Intent(this, PaymentDetails::class.java)
                    startActivity(intent)
                    drawerLayout.closeDrawer(Gravity.LEFT)
                }

                R.id.item2 -> {
                    val editor = sharedPreferences!!.edit()
                    editor.putInt("key", 0)
                    editor.apply()
                    progressBar.isVisible = true
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                    Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                }

            }
            true

        }

        prashannar.setOnClickListener {
            val queryUrl: Uri = Uri.parse(github)
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }
        getUserData()

        //notification
        createNotificationChannel()

        val notification = NotificationCompat.Builder(this, Channel_ID)
            .setContentTitle("Test title")
            .setContentText("Test Text")
            .setSmallIcon(R.drawable.icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.notify(notification_ID, notification)


    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Channel_ID, Channel_Name,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.BLUE
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)



        }
    }

    private fun getUserData() {
        lifecycleScope.launchWhenCreated {
            progressBar.isVisible = true

            val response = try {
                Instance.api.getUser("2d68c1ed7caa45")

            } catch (e: IOException) {
                Log.e("Retrofit", "No Internet Connection")
                progressBar.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("Retrofit", "Unexpected response")
                progressBar.isVisible = false
                return@launchWhenCreated
            }

            //on success
            if (response.isSuccessful && response.body() != null) {
                //nameTV.text = "Name: ${response.body()!!.name}"
                Log.d("retro", response.body().toString())
                val details = response.body()
                nameTV.text = "Name: ${details!!.name}"
                ageTV.text = "Age: ${details.age}"
                dobTV.text = "DOB: ${details.dob}"

            } else {
                Log.d("error", response.code().toString())
            }
            progressBar.isVisible = false

        }
    }

    //prevents closing of app when slide menu is open
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT)
        } else {
            super.onBackPressed()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        getUserData()
        super.onResume()
    }

    override fun onStart() {
        getUserData()
        super.onStart()
    }
}