package com.mobiledevelopment.ucrefillsystem.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobiledevelopment.ucrefillsystem.R
import kotlinx.android.synthetic.main.item_voucher.view.*

class VoucherAdapter(private val context: Context) :
    RecyclerView.Adapter<VoucherAdapter.ViewHolder>() {
    var vouchers = mutableListOf<Any>()
    fun addVoucher(list: List<Any>) {
        vouchers.clear()
        vouchers.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_voucher,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindContent(vouchers[position] as Int, position)
    }

    override fun getItemCount(): Int = vouchers.size

    inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.img_item_voucher

        fun bindContent(link: String) {
            Glide.with(context).load(link).into(image)
        }

        fun bindContent(voucher: Int, position: Int) {
            Glide.with(context).load(voucher).into(image)

            if (position != vouchers.size - 1) {
                val params = RelativeLayout.LayoutParams(900, 360)
                params.setMargins(0, 0, 16, 0)
                view.layoutParams = params
            }
        }
    }
}