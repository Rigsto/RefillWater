package com.mobiledevelopment.ucrefillsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.cardview_history.view.*


class adapter_history(private val list:ArrayList<model_history>) : RecyclerView.Adapter<adapter_history.Holder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_history,parent,false))
    }

    override fun getItemCount(): Int = list?.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.view.txt_jumlah.text = list?.get(position)?.name

    }

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

}