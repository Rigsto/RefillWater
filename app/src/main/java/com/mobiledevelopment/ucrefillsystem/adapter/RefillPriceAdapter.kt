package com.mobiledevelopment.ucrefillsystem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.model.RefillPrice
import kotlinx.android.synthetic.main.item_refillprice.view.*

class RefillPriceAdapter(
    private val context: Context,
    private val listener: (RefillPrice) -> Unit
) :
    RecyclerView.Adapter<RefillPriceAdapter.PriceViewHolder>() {

    private var x = -1

    private var refillPrice: List<RefillPrice> = emptyList()
    fun addList(list: List<RefillPrice>) {
        this.refillPrice = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PriceViewHolder {
        return PriceViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_refillprice,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {
        holder.bindContent(refillPrice[position], listener)
        holder.rb_price.isChecked = position == x
    }

    override fun getItemCount(): Int = refillPrice.size

    inner class PriceViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val rb_price = view.rb_refillprice

        fun bindContent(refill: RefillPrice, listener: (RefillPrice) -> Unit) {
            rb_price.text = "${refill.size} ml"

            view.setOnClickListener {
                x = adapterPosition
                notifyDataSetChanged()
                listener(refill)
            }
        }
    }
}