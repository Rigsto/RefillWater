package com.mobiledevelopment.ucrefillsystem.adapter.gallon_volume

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.getFloor
import com.mobiledevelopment.ucrefillsystem.helper.getRemainPercentage
import com.mobiledevelopment.ucrefillsystem.model.Gallon
import kotlinx.android.synthetic.main.item_gallon_volume.view.*

class GallonVolumeAdapter(private val context: Context, private val gallons: List<Gallon>) :
    RecyclerView.Adapter<GallonVolumeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_gallon_volume,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindContent(gallons[position])
    }

    override fun getItemCount(): Int {
        return gallons.size
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val floor = view.tv_gallon_place
        private val pbremain = view.pb_gallon_remain
        private val remain = view.tv_gallon_remain

        fun bindContent(gallon: Gallon) {
            floor.text = "Lantai ${gallon.id?.getFloor()}"
            pbremain.progress = context.getRemainPercentage(gallon.remain, gallon.max)
            remain.text = "${gallon.remain} ml"

            if (context.getRemainPercentage(gallon.remain.toDouble(), gallon.max.toDouble()) < 25.0)
                pbremain.progressDrawable =
                    ContextCompat.getDrawable(context, R.drawable.progressbar_red)
        }
    }
}