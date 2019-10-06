package com.mobiledevelopment.ucrefillsystem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.readableNumber
import com.mobiledevelopment.ucrefillsystem.model.Refill
import com.mobiledevelopment.ucrefillsystem.model.TopUp
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val context: Context) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var history: List<Any> = emptyList()

    fun addList(list: List<Any>) {
        this.history = list
        notifyDataSetChanged()
    }

    companion object {
        private const val ITEM_TOPUP = 0;
        private const val ITEM_REFILL = 1;
        private const val ITEM_TRANSFER = 2;
        private const val ITEM_TRANSFERED = 3;
        private const val ITEM_WITHDRAW = 4;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_history,
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return when (history[position]) {
            is TopUp -> ITEM_TOPUP
            is Refill -> ITEM_REFILL
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        when (holder.itemViewType) {
            ITEM_TOPUP -> holder.bindContent(history[position] as TopUp)
            ITEM_REFILL -> holder.bindContent(history[position] as Refill)
            else -> throw IllegalArgumentException("Undefined view type")
        }
    }

    override fun getItemCount(): Int = history.size

    inner class HistoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val img_logo = view.img_history_logo
        private val tv_logo = view.tv_history_logo
        private val name = view.tv_history_name
        private val subname = view.tv_history_subname
        private val balance = view.tv_history_balance

        fun bindContent(topUp: TopUp) {
            Glide.with(context).load(R.drawable.bg_history_topup).into(img_logo)
            tv_logo.text = "+"
            name.text = "Top Up"
            subname.text = ""
            balance.text = "+ Rp. ${topUp.balance.readableNumber()}"
            balance.setTextColor(ContextCompat.getColor(context, R.color.b4_success))
        }

        fun bindContent(refill: Refill) {
            Glide.with(context).load(R.drawable.bg_available).into(img_logo)
            tv_logo.text = "R"
            name.text = "Refill ${refill.size}ml"
            subname.text = "${refill.place}, Floor ${refill.floor}"
            balance.text = "- Rp. ${refill.balance.readableNumber()}"
            balance.setTextColor(ContextCompat.getColor(context, R.color.b4_danger))
        }
    }
}