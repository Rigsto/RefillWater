package com.mobiledevelopment.ucrefillsystem.adapter.choosebottle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.model.Bottle
import kotlinx.android.synthetic.main.item_choose_bottle.view.*

class MyBottleAdapter(private val context: Context, private val listener: (Bottle) -> Unit) :
    RecyclerView.Adapter<MyBottleAdapter.ViewHolder>() {
    var list = mutableListOf<Bottle>()
    fun addList(list: List<Bottle>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_choose_bottle,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindContent(list[position], listener)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val size = view.tv_choose_bottle_size

        fun bindContent(bottle: Bottle, listener: (Bottle) -> Unit) {
            size.text = "${bottle.capacity} ml"

            view.setOnClickListener {
                listener(bottle)
            }
        }
    }
}