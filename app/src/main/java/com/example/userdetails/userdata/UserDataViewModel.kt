package com.example.userdetails.userdata

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.userdetails.R
import com.example.userdetails.apis.ApiInterface
import com.example.userdetails.apis.RetrofitClient
import com.example.userdetails.const.*
import com.example.userdetails.models.DataModel
import com.example.userdetails.ui.Resource
import com.example.userdetails.ui.ResponseCodeCheck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class UserDataViewModel (application: Application): AndroidViewModel(application) {


   lateinit var userDataRepository: UserDataRepository

    var responseCodeCheck: ResponseCodeCheck = ResponseCodeCheck()
    var mutableLiveData: MutableLiveData<Resource<DataModel>> = MutableLiveData()
    var liveData: MutableLiveData<Resource<DataModel>> = mutableLiveData


    fun userDataList(){

        val hashMap : HashMap<String,String> = HashMap()
        hashMap.apply {
            put(PARAM,"1")
        }

        mutableLiveData.value = Resource.loading(null)

        val apiInterface = RetrofitClient.getRetrofit().create(ApiInterface::class.java)
        userDataRepository = UserDataRepository(apiInterface)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val repository : Response<DataModel> = userDataRepository.userData(hashMap)
                mutableLiveData.postValue(responseCodeCheck.getResponseResult(repository))

            }catch (e: Exception){

                Log.d("data_info", e.message.toString())
                liveData.postValue(Resource.error(getApplication<Application>().getString(R.string.something_went_wrong), null))
            }
        }
    }
}