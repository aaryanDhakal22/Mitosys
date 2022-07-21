package com.prashannar.mitosys

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Instance {
    //TODO: change the url

    val api: MitosysApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://anmolsec.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MitosysApi::class.java)

    }

}