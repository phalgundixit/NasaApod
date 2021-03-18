package com.example.nasaapod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nasaapod.data.ImageResponseItem
import com.example.nasaapod.databinding.ItemDisplayImageBinding


class ImageAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<ImageResponseItem, ImageAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemDisplayImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

            if (currentItem != null) {
                holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: ItemDisplayImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: ImageResponseItem) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.url)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)

                textViewUserName.text = photo.copyright
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: ImageResponseItem)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<ImageResponseItem>() {
            override fun areItemsTheSame(oldItem: ImageResponseItem, newItem: ImageResponseItem) =
                oldItem.copyright == newItem.copyright

            override fun areContentsTheSame(oldItem: ImageResponseItem, newItem: ImageResponseItem) =
                oldItem == newItem
        }
    }
}