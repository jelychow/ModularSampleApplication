// (c)2016 Flipboard Inc, All Rights Reserved.

package com.bxjr.supplychain.test

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.zhou.captain.loginlib.R
import com.zhou.captain.sdklib.rx.GankBeauty
import kotlinx.android.synthetic.main.item_z_b.view.*


class ZhuangbiListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    internal var images: List<GankBeauty>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_z_b, parent, false)
        return DebounceViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val debounceViewHolder = holder as DebounceViewHolder
        val image = images!![position]
        holder.bindData(image)
    }

    override fun getItemCount(): Int {
        return if (images == null) 0 else images!!.size
    }

    fun setImages(images: List<GankBeauty>) {
        this.images = images
        notifyDataSetChanged()
    }

    internal class DebounceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindData( image: GankBeauty) {

            image.url?.let {
                Glide.with(itemView.context).load(it).into(itemView.im_pic)
            }
        }
    }

}
