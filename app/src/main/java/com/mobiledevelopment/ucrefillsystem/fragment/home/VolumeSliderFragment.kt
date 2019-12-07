package com.mobiledevelopment.ucrefillsystem.fragment.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.helper.gone
import com.mobiledevelopment.ucrefillsystem.helper.invisible
import com.mobiledevelopment.ucrefillsystem.helper.visible
import com.mobiledevelopment.ucrefillsystem.model.response.CodeResponse
import com.mobiledevelopment.ucrefillsystem.network.ApiRepository
import com.mobiledevelopment.ucrefillsystem.presenter.FillMyBottlePresenter
import com.mobiledevelopment.ucrefillsystem.viewinterface.CodeView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_volume_slider.*

/**
 * A simple [Fragment] subclass.
 */
class VolumeSliderFragment : Fragment(), CodeView, View.OnClickListener,
    SeekBar.OnSeekBarChangeListener {
    private lateinit var presenter: FillMyBottlePresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_volume_slider, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter = FillMyBottlePresenter(this, ApiRepository(), Gson())

        btn_choose_fill.setOnClickListener(this)
        sb_choose_size.setOnSeekBarChangeListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_choose_fill -> {
                presenter.fillMyBottle()
            }
        }
    }

    override fun showLoading() {
        btn_choose_fill.invisible()
        pb_fill_my_bottle.visible()
    }

    override fun hideLoading() {
        btn_choose_fill.visible()
        pb_fill_my_bottle.gone()
    }

    override fun dataSuccess(data: CodeResponse) {
        fragmentManager?.beginTransaction()?.replace(R.id.fl_container_home, HomeFragment())
            ?.commit()
        activity?.bottom_navigation?.menu?.getItem(0)?.setChecked(true)
        activity?.img_bnv?.visible()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        when (seekBar.id) {
            R.id.sb_choose_size -> {
                tv_choose_size.text = "${progress} ml"
            }
        }
    }
}
