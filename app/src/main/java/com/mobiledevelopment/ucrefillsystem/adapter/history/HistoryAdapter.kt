package com.mobiledevelopment.ucrefillsystem.adapter.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.gone
import com.mobiledevelopment.ucrefillsystem.model.History
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val context: Context, private val historys: List<History>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    var count = historys.size
    var type = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_history,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindContent(historys[position])
    }

    override fun getItemCount(): Int = count

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val place = view.tv_history_place
        private val amount = view.tv_history_size
        private val price = view.tv_history_point
        private val time = view.tv_history_time
        private val card = view.cv_summary

        fun bindContent(history: History) {
            place.text = history.place
            amount.text = "${history.amount} ml"
            price.text = history.price.toString()
            time.text = history.date

            if (type == 1) {
                time.gone()
                card.elevation = 0F

                val params = card.layoutParams as RecyclerView.LayoutParams
                params.setMargins(0, -16, 0, -16)
                card.layoutParams = params
            }
        }
    }
}