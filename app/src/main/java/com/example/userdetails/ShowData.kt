package com.example.userdetails

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.service.autofill.UserData
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.userdetails.databinding.ActivityShowDataBinding
import com.example.userdetails.models.Data
import com.example.userdetails.models.DataModel
import kotlinx.android.synthetic.main.activity_show_data.*
import kotlinx.android.synthetic.main.item_row_data.*

class ShowData : AppCompatActivity() {

    lateinit var showData : Data
    lateinit var binding: ActivityShowDataBinding

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_show_data)


        if (intent!=null)
        {
            showData = intent.extras?.getSerializable("data")as Data
            binding.showName.text = showData.name
            binding.showEmail.text = showData.email
            Glide.with(this).load(showData.profile_picture).into(binding.showImage)


        }


    }



}

