package com.example.userdetails.userdata

import com.example.userdetails.apis.ApiInterface
import com.example.userdetails.models.DataModel
import retrofit2.Response

class UserDataRepository(var apiInterface: ApiInterface) {

    suspend fun userData(hashMap: HashMap<String,String>): Response<DataModel> {
        return apiInterface.userData(hashMap)

    }


}