package com.alejoestrada.misdeudores.AlimentosTab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                alimentosTotales()
            }
            else -> {
                return alimentosFavs()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Alimentos"
            else -> {
                return "Alimentos Favoritos"
            }
        }
    }
}