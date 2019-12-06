package com.mobiledevelopment.ucrefillsystem.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.model.Gallon
import kotlinx.android.synthetic.main.item_gallon_volume_short.view.*

class GallonVolumeShortAdapter(private val context: Context) :
    RecyclerView.Adapter<GallonVolumeShortAdapter.ViewHolder>() {
    var list = mutableListOf<Gallon>()
    fun addList(list: List<Gallon>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_gallon_volume_short,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindContent(list[position])
    }

    override fun getItemCount(): Int {
        return Math.min(list.size, 3)
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val floor = view.tv_gallon_short_place
        private val remain = view.pb_gallon_short_remain

        fun bindContent(gallon: Gallon) {
            floor.text = "Lantai ${gallon.floor}"
            remain.progress = gallon.remainPercentage.toInt()
        }
    }
}