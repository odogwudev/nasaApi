package com.odogwudev.nasaapi.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.odogwudev.nasaapi.R
import com.odogwudev.nasaapi.view.adapters.MainPagerAdapter
import com.odogwudev.nasaapi.view.fragments.AsteroidsFragment
import com.odogwudev.nasaapi.view.fragments.DailyPostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewPager = main_viewpager
        val mainPagerAdapter = MainPagerAdapter.Builder(supportFragmentManager)
            .addFragment("DailyPost", DailyPostFragment())
            .addFragment("Asteroids", AsteroidsFragment())
            .create()
        viewPager.adapter = mainPagerAdapter
        val tabLayout = main_tab_layout
        tabLayout.setupWithViewPager(viewPager)
    }
}