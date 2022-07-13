package com.prashannar.mitosys

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MitosysApi {
    //unid 62cd0014bd7449355b9e5ace
    //TODO: change the request, add query and modify userDetails according to the data sent


    @GET("details/{unID}")
    suspend fun getUser(
        @Path("unID") unID : String
    ) : Response<UserDetails>


}