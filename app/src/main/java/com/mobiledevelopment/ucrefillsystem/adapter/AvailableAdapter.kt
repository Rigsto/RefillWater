package com.mobiledevelopment.ucrefillsystem.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.model.Dispenser
import kotlinx.android.synthetic.main.item_available.view.*
import kotlinx.android.synthetic.main.item_header.view.*

class AvailableAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var dispensers: List<Any> = emptyList()

    fun addList(list: List<Any>) {
        this.dispensers = list
        notifyDataSetChanged()
    }

    companion object {
        private const val ITEM_HEADER = 0
        private const val ITEM_DATA = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_HEADER -> HeaderViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_header,
                    parent,
                    false
                )
            )
            ITEM_DATA -> AvailableViewHolder(
                LayoutInflater.from(context).inflate(
                    R.layout.item_available,
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dispensers[position]) {
            is String -> ITEM_HEADER
            is Dispenser -> ITEM_DATA
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_HEADER -> {
                val headerHolder = holder as HeaderViewHolder
                headerHolder.bindContent(dispensers[position] as String)
            }
            ITEM_DATA -> {
                val itemHolder = holder as AvailableViewHolder
                itemHolder.bindContent(dispensers[position] as Dispenser)
            }
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = dispensers.size

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val header = view.tv_header

        fun bindContent(text: String) {
            header.text = text
        }
    }

    inner class AvailableViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val img_stat = view.img_available_bg
        private val floor = view.tv_available_floor
        private val place = view.tv_available_name
        private val remain = view.pb_remain

        fun bindContent(dispenser: Dispenser) {
            if (dispenser.remain <= 2) {
                Glide.with(context).load(R.drawable.bg_not_available).into(img_stat)
            } else {
                Glide.with(context).load(R.drawable.bg_available).into(img_stat)
            }

            floor.text = dispenser.floor
            place.text = dispenser.place
            remain.progress = dispenser.remain.toInt()

            if (dispenser.remain >= 25) {
                remain.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.b4_success))
            } else if (dispenser.remain >= 10) {
                remain.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.b4_warning))
            } else if (dispenser.remain >= 2) {
                remain.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.b4_danger))
            } else {
                remain.progressTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.b4_secondary))
            }
        }
    }
}