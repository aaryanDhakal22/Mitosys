package com.prashannar.mitosys

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.activity_payment_details.*
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception

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

        saveBtn.setOnClickListener {
            val bitmap: Bitmap = Screenshot.takeScreenShotOfRoot(ssIV)
            saveImage(bitmap)
            ssIV.setImageBitmap(bitmap)
            Log.d("saved", bitmap.toString())
            Toast.makeText(this, "Captured", Toast.LENGTH_SHORT).show()

        }
    }

    companion object Screenshot{
           private fun takeScreenShot(view: View): Bitmap{
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache(true)
            val bitmap = Bitmap.createBitmap(view.drawingCache)
            view.isDrawingCacheEnabled = false
            return bitmap
        }
        fun takeScreenShotOfRoot(v: View): Bitmap{
            return takeScreenShot(v.rootView)
        }
    }

    private fun saveImage(bit: Bitmap){
        val root = Environment.getExternalStorageDirectory().absolutePath
        val myDir: File = File(root + "/saved_images")
        myDir.mkdirs()

        val fName = "Image-"+ 0 +".jpg"
        val file: File = File(myDir, fName)
        if(file.exists()) file.delete()

        try {
            val out : FileOutputStream =  FileOutputStream(file)
            bit.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
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