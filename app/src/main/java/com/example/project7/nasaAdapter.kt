package com.example.project7

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class nasaAdapter(private val nasaList: List<String>, private val idList: List<String>, private val solList: List<String>, private val dateList: List<String>) : RecyclerView.Adapter<nasaAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nasaImage: ImageView
        val idOutput : TextView
        val solOutput : TextView
        val dateOutput : TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            nasaImage = view.findViewById(R.id.nasa_image)
            idOutput = view.findViewById(R.id.idOutput)
            solOutput = view.findViewById(R.id.solOutput)
            dateOutput = view.findViewById(R.id.dateOutput)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.nasa_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(nasaList[position])
            .centerCrop()
            .into(holder.nasaImage)
        Log.d("Check", "${nasaList[position]}")

        holder.idOutput.text = "This is rover id ${idList[position]}"
        holder.solOutput.text = "This is sol number ${idList[position]}"
        holder.dateOutput.text = "This is earth date ${dateList[position]}"


        Log.d("here!","$nasaList")


    }

    override fun getItemCount() = nasaList.size
}