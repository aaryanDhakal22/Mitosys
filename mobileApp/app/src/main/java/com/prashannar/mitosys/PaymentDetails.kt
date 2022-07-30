package com.prashannar.mitosys

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.*
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_payment_details.*
import retrofit2.HttpException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

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
            ssIV.setImageBitmap(bitmap)
            val imageHandler = Handler(Looper.getMainLooper()).postDelayed({
                ssIV.isVisible = false
            }, 5000)
            saveToGallery(this, bitmap, "Mitosys")
        }
    }

    fun saveToGallery(context: Context, bitmap: Bitmap, albumName: String) {
        val filename = "${System.currentTimeMillis()}.png"
        val write: (OutputStream) -> Boolean = {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    "${Environment.DIRECTORY_DCIM}/$albumName"
                )

            }
            context.contentResolver.let {
                it.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)?.let { uri ->
                    it.openOutputStream(uri)?.let(write)
                }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
                    .toString() + File.separator + albumName
            val file = File(imagesDir)
            if (!file.exists()) {
                file.mkdir()
            }
            val image = File(imagesDir, filename)
            write(FileOutputStream(image))
        }
    }

    companion object Screenshot {
        private fun takeScreenShot(view: View): Bitmap {
            view.isDrawingCacheEnabled = true
            view.buildDrawingCache(true)
            val bitmap = Bitmap.createBitmap(view.drawingCache)
            view.isDrawingCacheEnabled = false
            return bitmap
        }

        fun takeScreenShotOfRoot(v: View): Bitmap {
            return takeScreenShot(v.rootView)
        }
    }


    private fun getUserData() {
        lifecycleScope.launchWhenCreated {
            progressBarDetails.isVisible = true

            val response = try {
                Instance.api.getUser("2d68c1ed7caa45")

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
                tuitionTV.text = "Rs. ${details?.tuition}"
                snacksTV.text = "Rs. ${details?.snacks}"


            } else {
                Log.d("error", response.code().toString())
            }
            progressBarDetails.isVisible = false

        }
    }
}