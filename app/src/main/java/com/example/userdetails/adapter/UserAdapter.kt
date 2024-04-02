package com.example.userdetails.adapter

import android.content.ComponentCallbacks
import android.content.Context
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.userdetails.R
import com.example.userdetails.models.Data
import com.example.userdetails.models.DataModel

class UserAdapter(private val myList: Array<Data>,var context: Context, val callbacks: (Data, Int, View) -> Unit) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var name : TextView = itemView.findViewById(R.id.userName)
        var email : TextView = itemView.findViewById(R.id.userEmail)
        var image : ImageView = itemView.findViewById(R.id.userProfile)
        var con : ConstraintLayout = itemView.findViewById(R.id.con)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder  {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_data, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myData = myList[position]
        holder.name.text = myData.name
        holder.email.text = myData.email
        Glide.with(context).load(myData.profile_picture).into(holder.image)

        holder.itemView.setOnClickListener {
            callbacks.invoke(myList[position],position,holder.image)
        }
    }
    override fun getItemCount(): Int = myList.size

}




