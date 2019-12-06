package com.example.soccermatch.ui.team.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.soccermatch.R
import com.example.soccermatch.utils.GlideApp
import kotlinx.android.synthetic.main.item_picture.view.*

class GaleryAdapter :RecyclerView.Adapter<GaleryAdapter.GaleryViewHolder>(){

    private var urls = emptyList<String>()

    internal fun setUrls(urls: List<String>){
        this.urls = urls
        notifyDataSetChanged()
    }

    class GaleryViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(url:String) = with(itemView){
            GlideApp.with(this)
                .load(url)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
                .into(item_picture_img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_picture,parent,false)
        return GaleryViewHolder(view)
    }

    override fun getItemCount()=urls.size

    override fun onBindViewHolder(holder: GaleryViewHolder, position: Int) {
        holder.bind(urls[position])
    }
}