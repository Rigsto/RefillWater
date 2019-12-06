package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobiledevelopment.ucrefillsystem.GallonVolumeActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.SummaryActivity
import com.mobiledevelopment.ucrefillsystem.adapter.home.GallonVolumeShortAdapter
import com.mobiledevelopment.ucrefillsystem.adapter.home.VoucherAdapter
import kotlinx.android.synthetic.main.fragment_home.*


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), View.OnClickListener {
    private lateinit var voucherAdapter: VoucherAdapter
    private lateinit var gallonVolumeShortAdapter: GallonVolumeShortAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        voucherAdapter = VoucherAdapter(context!!)
        rv_home_voucher.adapter = voucherAdapter
        rv_home_voucher.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_home_voucher.setHasFixedSize(true)
        rvi_home_voucher.setRecyclerView(rv_home_voucher)
        rvi_home_voucher.forceUpdateItemCount()
        rvi_home_voucher.setCurrentPosition(0)
        rv_home_volume.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                when (newState) {
                    RecyclerView.SCROLL_STATE_IDLE -> {
                        val position =
                            ((recyclerView.layoutManager) as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()
                        rvi_home_voucher.setCurrentPosition(position)
                    }
                }
            }
        })

        gallonVolumeShortAdapter = GallonVolumeShortAdapter(context!!)
        rv_home_volume.adapter = gallonVolumeShortAdapter
        rv_home_volume.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_home_volume.setHasFixedSize(true)

        tv_voucher_viewall.setOnClickListener(this)
        tv_volume_viewall.setOnClickListener(this)
        tv_hydration_check.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_voucher_viewall -> {
            }
            R.id.tv_volume_viewall -> {
                startActivity(Intent(activity, GallonVolumeActivity::class.java))
            }
            R.id.tv_hydration_check -> {
                startActivity(Intent(activity, SummaryActivity::class.java))
            }
        }
    }
}
