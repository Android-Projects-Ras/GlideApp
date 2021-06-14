package com.rogok.glideapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.rogok.glideapp.databinding.ItemBinding
import com.rogok.glideapp.models.User

class UserPagingDataAdapter : PagingDataAdapter<User, UserPagingDataAdapter.UserViewHolder>(
    IMAGE_COMPARATOR
) {

    companion object {
        private val IMAGE_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem.albumId == newItem.albumId &&
                        oldItem.title == newItem.title &&
                        oldItem.url == newItem.url
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    inner class UserViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            val url = GlideUrl(
                user.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )

            binding.apply {
                Glide.with(itemView)
                    .load(url)
                    .centerCrop()
                    //.transition(DrawableTransitionOptions.withCrossFade())
                    .into(binding.ivItem)

                binding.tvTitle.text = user.title
                binding.tvId.text = user.id.toString()
            }
        }
    }
}

