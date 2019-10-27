package com.rewallution.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.rewallution.R
import com.rewallution.model.Wallpaper
import com.rewallution.screen.WallpaperListActivity
import kotlinx.android.synthetic.main.item_loader.view.*
import kotlinx.android.synthetic.main.item_wallpaper.view.*

class WallpaperListAdapter(
    private val activity: Activity,
    private val wallpaperListActivity: WallpaperListActivity,
    private val itemClickListener: OnItemClickListener,
    private var wallpaperList: ArrayList<Wallpaper>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val viewTypeItem = 0
    private val viewTypeLoading = 1

    override fun getItemViewType(position: Int): Int {

        return if (position == wallpaperList.size) {
            viewTypeLoading
        } else {
            viewTypeItem
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == viewTypeItem) {
            val view = LayoutInflater.from(activity).inflate(R.layout.item_wallpaper, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(activity).inflate(R.layout.item_loader, parent, false)
            LoaderViewHolder(view)
        }

    }

    override fun getItemCount(): Int {
        return wallpaperList.size + 1
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ItemViewHolder) {
            Glide.with(activity)
                .load(wallpaperList[position].imageUrl!!.regular)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(activity.getDrawable(R.drawable.placeholder))
                .error(activity.getDrawable(R.drawable.placeholder))
                .into(holder.image)
        } else if (holder is LoaderViewHolder) {
            holder.loader.isIndeterminate = true
        }

        if (position == itemCount - 1)
            wallpaperListActivity.onLastItemReached()
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.ivFav!!

        init {
            view.setOnClickListener {
                itemClickListener.onItemClick(adapterPosition)
            }
        }
    }

    inner class LoaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val loader = view.loader!!
    }

}