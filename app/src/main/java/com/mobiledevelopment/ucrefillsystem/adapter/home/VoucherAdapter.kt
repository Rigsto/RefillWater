package com.mobiledevelopment.ucrefillsystem.adapter.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mobiledevelopment.ucrefillsystem.R
import kotlinx.android.synthetic.main.item_voucher.view.*

class VoucherAdapter(private val context: Context) :
    RecyclerView.Adapter<VoucherAdapter.ViewHolder>() {
    var vouchers = mutableListOf<String>()
    fun addVoucher(list: List<String>) {
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
        holder.bindContent(R.drawable.carousel)
    }

    override fun getItemCount(): Int = 3

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.img_item_voucher

        fun bindContent(link: String) {
            Glide.with(context).load(link).into(image)
        }

        fun bindContent(voucher: Int) {
            Glide.with(context).load(voucher).into(image)
        }
    }
}