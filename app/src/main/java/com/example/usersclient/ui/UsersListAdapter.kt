package com.example.usersclient.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usersclient.R
import com.example.usersclient.data.repositories.User

class UsersListAdapter(var context: Context, val click: (User) -> Unit) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    var usersList: MutableList<User> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_main, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(usersList[position].avatar).into(holder.avatarImageView)
        holder.userNameTextView.text = usersList[position].first_name + " " + usersList[position].last_name
        holder.itemView.setOnClickListener {
            click.invoke(usersList[position])
        }
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val avatarImageView: ImageView = itemView.findViewById(R.id.avatarImageView)
        val userNameTextView: TextView = itemView.findViewById(R.id.userNameTextView)
    }

}