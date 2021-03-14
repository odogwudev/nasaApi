package com.odogwudev.nasaapi.view.fragments

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.odogwudev.nasaapi.model.DailyPost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyPostFragment : Fragment() {
    private lateinit var currentView: View
    private val viewModel: DailyPostViewModel by viewModels()
}