package com.odogwudev.nasaapi.view.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.lang.IllegalStateException

class MainPagerAdapter private constructor(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    private var titlesList = mutableListOf<String>()
    private var fragmentsList = mutableListOf<Fragment>()

    override fun getItem(position: Int) = fragmentsList[position]

    override fun getCount() = fragmentsList.size

    override fun getPageTitle(position: Int) = titlesList[position]

    class Builder(manager: FragmentManager) {
        private var result = MainPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)

        fun addFragment(title: String, fragment: Fragment): Builder {
            result.titlesList.add(title)
            result.fragmentsList.add(fragment)
            return this
        }

        fun create(): MainPagerAdapter {
            if (result.fragmentsList.size == 0) throw IllegalStateException("Cannot create MainPagerAdapter without a single fragment!")
            return result
        }
    }

}