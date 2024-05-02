package com.example.a01_wskpolice.adapters

import androidx.recyclerview.widget.RecyclerView
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.a01_wskpolice.R

class PhotoRobotAdapter(private val images: List<Drawable>) : RecyclerView.Adapter<PhotoRobotAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_newphoto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageFile = images[position]
        val bitmap = BitmapFactory.decodeFile(imageFile.toString())
        holder.imageView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.photoRobotIMG)
    }
}