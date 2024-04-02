package com.example.userdetails.userdata

import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdetails.R
import com.example.userdetails.ShowData
import com.example.userdetails.adapter.UserAdapter
import com.example.userdetails.databinding.ActivityUserDataBinding
import com.example.userdetails.models.DataModel
import com.example.userdetails.ui.status



class UserDataActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserDataBinding
    lateinit var userDataViewModel: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_data)
        userDataViewModel = ViewModelProvider(this)[UserDataViewModel::class.java]

        userDataViewModel.userDataList()

        userDataViewModel.liveData.observe(this) {

            when (it.status) {
                status.SUCCESS -> {

                    Log.d("data_information", "status.SUCCESS")
                    Toast.makeText(this@UserDataActivity, it.data?.message, Toast.LENGTH_SHORT).show()

                    val recyclerview = binding.recyclerView
                    recyclerview.layoutManager = LinearLayoutManager(this)

                    val adapter = UserAdapter(it.data?.data!!,this  ){ DataModel, _, _ ->
                        val i = Intent(this,ShowData::class.java)
                        i.putExtra("data",DataModel)
                        startActivity(i)

                    }
                    recyclerview.adapter = adapter
                }
                status.LOADING-> {
                    Log.d("data_information", "status.LOADING")
                }
                status.ERROR -> {
                    Log.d("data_information", "status.ERROR")
            }
        }


    }
}
}