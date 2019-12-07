package com.mobiledevelopment.ucrefillsystem.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.mobiledevelopment.ucrefillsystem.LoginActivity
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.adapter.onboarding.OnboardingAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnboardingActivity : AppCompatActivity(), ViewPager.OnPageChangeListener,
    View.OnClickListener {
    private lateinit var onboardingAdapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportActionBar?.hide()

        onboardingAdapter = OnboardingAdapter(supportFragmentManager)
        vp_onboarding.adapter = onboardingAdapter

        vp_onboarding.addOnPageChangeListener(this)

        btn_onboarding_getstarted.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_onboarding_getstarted -> {
                startActivity(Intent(this, LoginActivity::class.java))
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            }
        }
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        Glide.with(this).load(R.drawable.circle_blue_light).into(img_onboarding_indicator_0)
        Glide.with(this).load(R.drawable.circle_blue_light).into(img_onboarding_indicator_1)
        Glide.with(this).load(R.drawable.circle_blue_light).into(img_onboarding_indicator_2)

        val pos = position % 3

        when (pos) {
            0 -> {
                Glide.with(this).load(R.drawable.circle_blue).into(img_onboarding_indicator_0)
            }
            1 -> {
                Glide.with(this).load(R.drawable.circle_blue).into(img_onboarding_indicator_1)
            }
            2 -> {
                Glide.with(this).load(R.drawable.circle_blue).into(img_onboarding_indicator_2)
            }
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }
}
