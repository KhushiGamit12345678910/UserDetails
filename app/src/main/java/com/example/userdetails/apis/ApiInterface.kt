package com.example.userdetails.apis

import com.example.userdetails.models.DataModel
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

   @FormUrlEncoded
    @POST("users.php")
     suspend fun userData(
        @FieldMap hashMap: HashMap<String, String>
    ): Response<DataModel>
}




