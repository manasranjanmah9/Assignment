package com.assignment.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.ui.home.adapter.AboutListAdapter
import com.squareup.picasso.Picasso

class CommonUtils {
    companion object {

        @JvmStatic
        @BindingAdapter("app:setImageUrl")
        fun bindImageUrl(imageView: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView)
            }
        }

        /*@JvmStatic
        @BindingAdapter(value = ["setAdapter"])
        fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<AboutListAdapter.MyViewHolder>) {
            this.run {
                this.setHasFixedSize(true)
                this.adapter = adapter
            }
        }*/

        @JvmStatic
        @BindingAdapter("android:visibility")
        fun setVisibility(view: View, visible: Boolean) {
            view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
        }

        @JvmStatic
        @BindingAdapter("app:refreshingBySwipe")
        fun refreshing(view: View, visible: Boolean) {
            view.visibility = if (visible) View.INVISIBLE else View.VISIBLE
        }


    }
}