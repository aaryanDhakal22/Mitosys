package com.prashannar.mitosys

import retrofit2.Response
import retrofit2.http.GET

interface MitosysApi {

    //TODO: change the request, add query and modify userDetails according to the data sent
    @GET("/user")
    suspend fun getUser() : Response<UserDetails>
}