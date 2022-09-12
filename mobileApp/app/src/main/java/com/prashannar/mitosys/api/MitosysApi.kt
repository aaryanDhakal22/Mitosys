package com.prashannar.mitosys.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MitosysApi {

    //details
    @GET("student/details/{unID}")
    suspend fun getUser(
        @Path("unID") unID: String
    ): Response<UserDetails>

    @GET("fee/status/{unID}")
    suspend fun getFeeStatus(
        @Path("unID") unID: String
    ): Response<FeeStatus>


}