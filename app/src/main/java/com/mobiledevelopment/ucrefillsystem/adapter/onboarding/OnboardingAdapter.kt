package com.mobiledevelopment.ucrefillsystem.adapter.onboarding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mobiledevelopment.ucrefillsystem.R
import com.mobiledevelopment.ucrefillsystem.fragment.intro.OnboardingFragment

class OnboardingAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val images =
        arrayOf(R.drawable.onboarding1, R.drawable.onboarding2, R.drawable.onboarding3)
    private val titles =
        arrayOf("Plastic Free Movement", "A New Way of Drinking", "Hydration Check")
    private val descs = arrayOf("Lorem Ipsum", "Lorem Ipsum", "Lorem Ipsum")

    override fun getCount(): Int {
        return titles.size
    }

    override fun getItem(position: Int): Fragment {
        return OnboardingFragment(images[position], titles[position], descs[position])
    }
}