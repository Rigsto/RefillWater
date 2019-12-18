package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.GallonVolumeActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.SummaryActivity
import com.mobiledevelopment.ucrefillsystem.adapter.home.GallonVolumeShortAdapter
import com.mobiledevelopment.ucrefillsystem.adapter.home.VoucherAdapter
import com.mobiledevelopment.ucrefillsystem.helper.SharedPreferenceKey
import com.mobiledevelopment.ucrefillsystem.helper.comingSoon
import com.mobiledevelopment.ucrefillsystem.helper.readableNumber
import com.mobiledevelopment.ucrefillsystem.helper.sharePref
import com.mobiledevelopment.ucrefillsystem.model.Gallon
import com.mobiledevelopment.ucrefillsystem.model.Voucher
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.GallonVolumePresenter
import com.mobiledevelopment.ucrefillsystem.presenter.VoucherPresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.GallonVolumeView
import com.mobiledevelopment.ucrefillsystem.viewinterface.VoucherView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), View.OnClickListener, VoucherView, GallonVolumeView {
    private lateinit var voucherAdapter: VoucherAdapter
    private lateinit var gallonVolumeShortAdapter: GallonVolumeShortAdapter
    private lateinit var voucherPresenter: VoucherPresenter
    private lateinit var gallonVolumePresenter: GallonVolumePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.setActionBar(tb_home)

        voucherPresenter = VoucherPresenter(this, ApiRepository(), Gson())
        voucherPresenter.getAllVoucher()

        gallonVolumePresenter = GallonVolumePresenter(this, ApiRepository(), Gson())
        gallonVolumePresenter.getGallons()

        voucherAdapter = VoucherAdapter(context!!)
        rv_home_voucher.adapter = voucherAdapter
        rv_home_voucher.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_home_voucher.setHasFixedSize(true)

        gallonVolumeShortAdapter = GallonVolumeShortAdapter(context!!)
        rv_home_volume.adapter = gallonVolumeShortAdapter
        rv_home_volume.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rv_home_volume.setHasFixedSize(true)

        tv_voucher_viewall.setOnClickListener(this)
        tv_volume_viewall.setOnClickListener(this)
        cv_hydration_level.setOnClickListener(this)
        btn_home_topup.setOnClickListener(this)

        val sp = context!!.sharePref()
        tv_home_name.text = "Hi, ${sp.getString(SharedPreferenceKey.NAME_KEY, "")}!"
        tv_home_nim.text = "${sp.getString(SharedPreferenceKey.EMAIL_KEY, "")}"
        tv_home_points.text = sp.getInt(SharedPreferenceKey.MONEY_KEY, 0).readableNumber()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_voucher_viewall -> {
                context?.comingSoon() //TODO bikin tampilan view all voucher
            }
            R.id.tv_volume_viewall -> {
                startActivity(Intent(activity, GallonVolumeActivity::class.java))
            }
            R.id.cv_hydration_level -> {
                startActivity(Intent(activity, SummaryActivity::class.java))
            }
            R.id.btn_home_topup -> {
                context?.comingSoon()
            }
        }
    }

    override fun showVouchers(vouchers: List<Voucher>) {
        voucherAdapter.addVoucher(vouchers)
    }

    override fun getGallons(gallons: List<Gallon>) {
        gallonVolumeShortAdapter.addList(gallons)
    }
}
