package com.prashannar.mitosys.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MitosysApi {
    //unid 62cd0014bd7449355b9e5ace
    //TODO: change the request, add query and modify userDetails according to the data sent

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