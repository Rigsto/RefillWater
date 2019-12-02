package com.mobiledevelopment.ucrefillsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.gallon_volume_list.view.*

class adapter_gallon(private val list:ArrayList<model_gallon>) : RecyclerView.Adapter<adapter_gallon.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.gallon_volume_list,parent,false))
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.txt_lantai.text = list?.get(position)?.name

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}