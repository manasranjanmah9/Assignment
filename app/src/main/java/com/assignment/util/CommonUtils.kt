package com.assignment.util

import android.content.Context
import android.net.ConnectivityManager
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.assignment.R
import com.squareup.picasso.Picasso


class CommonUtils {
    companion object {

        @JvmStatic
        @BindingAdapter("app:setImageUrl")
        fun bindImageUrl(imageView: ImageView, url: String?) {
            url?.let {
                Picasso.get()
                    .load(url)
                    .placeholder(R.drawable.image_holder)
                    .into(imageView)
            }
        }

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

        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.activeNetworkInfo.also {
                return it!!.isConnected
            }
        }

    }
}