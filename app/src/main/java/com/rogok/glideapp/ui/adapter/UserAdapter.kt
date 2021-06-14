package com.rogok.glideapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.rogok.glideapp.databinding.ItemBinding
import com.rogok.glideapp.models.User
import java.util.ArrayList


class UserAdapter(private var list: ArrayList<User>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //var userList = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        UserViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is UserViewHolder -> holder.bind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size

    inner class UserViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {




        fun bind(user: User) {

            val url = GlideUrl(
                user.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                binding.apply {
                    Glide.with(binding.root)
                        .load(url)
                        .into(binding.ivItem)

                    tvId.text = user.id.toString()
                    tvTitle.text = user.title
                }
            }
        }


    }

    fun setData(newList: ArrayList<User>) {
        list = newList
        notifyDataSetChanged()
    }


}