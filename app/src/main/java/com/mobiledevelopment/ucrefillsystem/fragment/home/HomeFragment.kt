package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobiledevelopment.ucrefillsystem.GallonVolumeActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.SummaryActivity
import com.mobiledevelopment.ucrefillsystem.adapter.home.GallonVolumeShortAdapter
import com.mobiledevelopment.ucrefillsystem.adapter.home.VoucherAdapter
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.getDummyGallonData
import com.mobiledevelopment.ucrefillsystem.helper.getDummyVoucherData
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import kotlinx.android.synthetic.main.fragment_home.*


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

        activity?.setActionBar(tb_home)

        voucherAdapter = VoucherAdapter(context!!)
        voucherAdapter.addVoucher(context!!.getDummyVoucherData())
        rv_home_voucher.adapter = voucherAdapter
        rv_home_voucher.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_home_voucher.setHasFixedSize(true)

        gallonVolumeShortAdapter = GallonVolumeShortAdapter(context!!)
        gallonVolumeShortAdapter.addList(context!!.getDummyGallonData())
        rv_home_volume.adapter = gallonVolumeShortAdapter
        rv_home_volume.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_home_volume.setHasFixedSize(true)

        tv_voucher_viewall.setOnClickListener(this)
        tv_volume_viewall.setOnClickListener(this)
        cv_hydration_level.setOnClickListener(this)

        val sp = context!!.sharePref()
        tv_home_name.text = "Hi, ${sp.getString(SharedPreferenceKey.fullname, "")}!"
        tv_home_nim.text = "NIM ${sp.getString(SharedPreferenceKey.nim, "")}"
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_voucher_viewall -> {
            }
            R.id.tv_volume_viewall -> {
                startActivity(Intent(activity, GallonVolumeActivity::class.java))
            }
            R.id.cv_hydration_level -> {
                startActivity(Intent(activity, SummaryActivity::class.java))
            }
        }
    }
}
