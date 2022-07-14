package com.prashannar.mitosys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_payment_details.*
import retrofit2.HttpException
import java.io.IOException

class PaymentDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_details)

        supportActionBar?.hide()


        backIV.setOnClickListener {
            val intent = Intent(this, HomeScreen::class.java)
            startActivity(intent)
            finish()
        }

        getUserData()
    }

    private fun getUserData() {
        lifecycleScope.launchWhenCreated {
            progressBarDetails.isVisible = true

            val response = try {
                Instance.api.getUser("62cd0014bd7449355b9e5ace")

            } catch (e: IOException) {
                Log.e("Retrofit", "No Internet Connection")
                progressBarDetails.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e("Retrofit", "Unexpected response")
                progressBarDetails.isVisible = false
                return@launchWhenCreated
            }

            //on success
            if (response.isSuccessful && response.body() != null) {
                //nameTV.text = "Name: ${response.body()!!.name}"
                Log.d("retro", response.body().toString())
                val details = response.body()
                speechTherapyTV.text = "Rs. ${details?.speechTherapy}"
                TherapyTV.text = "Rs. ${details?.therapy}"
                transportationTV.text = "Rs. ${details?.transportation}"
                admissionChargeTV.text = "Rs. ${details?.admissionCharge}"
                monthlyChargeTV.text = "Rs. ${details?.monthlyCharge}"
                snacksTV.text = "Rs. ${details?.snacks}"


            } else {
                Log.d("error", response.code().toString())
            }
            progressBarDetails.isVisible = false

        }
    }
}