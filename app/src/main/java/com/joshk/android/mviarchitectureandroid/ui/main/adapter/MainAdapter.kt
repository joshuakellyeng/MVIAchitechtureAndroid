package com.joshk.android.mviarchitectureandroid.ui.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bumptech.glide.Glide
import com.joshk.android.mviarchitectureandroid.R
import com.joshk.android.mviarchitectureandroid.data.model.User
import com.joshk.android.mviarchitectureandroid.databinding.ItemLayoutBinding

class MainAdapter(
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    private val users = mutableListOf<User>()

    class DataViewHolder(private val itemLayoutBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemLayoutBinding.root) {
        fun bind(user: User) {
            Log.d("MainAdapterBind", "${user.name}")
            itemLayoutBinding.textViewUserName.text = user.name
            itemLayoutBinding.textViewUserEmail.text = user.email
            Glide.with(itemLayoutBinding.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemLayoutBinding.imageViewAvatar)
        }
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = users.size

    fun addData(list: List<User>) {
        this.users.clear()
        this.users.addAll(list)
        notifyDataSetChanged()
    }
}


