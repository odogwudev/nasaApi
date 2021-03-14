package com.odogwudev.nasaapi.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.odogwudev.nasaapi.R

class AsteroidsFragment : Fragment() {

    private lateinit var currentView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentView = inflater.inflate(R.layout.asteroids_fragment_layout, container, false)
        return currentView
    }
}