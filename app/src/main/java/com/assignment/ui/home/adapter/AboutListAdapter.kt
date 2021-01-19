package com.assignment.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.data.About
import com.assignment.databinding.AboutListCellBinding
import com.assignment.util.CommonUtils.Companion.bindImageUrl
import com.squareup.picasso.Picasso

class AboutListAdapter(
    val context: Context,
    private val userListList: List<About>
) : RecyclerView.Adapter<AboutListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.about_list_cell, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val about = userListList[position]
        if (about.title.isNullOrEmpty()) {
            about.title = "NA"
        }
        if (about.description.isNullOrEmpty()) {
            about.description = "NA"
        }
        holder.binding.about = about
        holder.binding.imageUrl = about.imageHref

        holder.binding.executePendingBindings()

    }

    override fun getItemCount(): Int {
        return userListList.size
    }

    class MyViewHolder(val binding: AboutListCellBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}