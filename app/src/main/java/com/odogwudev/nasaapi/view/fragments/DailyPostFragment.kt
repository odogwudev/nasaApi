package com.odogwudev.nasaapi.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.odogwudev.nasaapi.R
import com.odogwudev.nasaapi.model.DailyPost
import com.odogwudev.nasaapi.utils.DataState
import com.odogwudev.nasaapi.viewmodel.DailyPostViewModel
import com.odogwudev.nasaapi.viewmodel.StateEvent
import com.odogwudev.repository.MainRepository.Companion.TAG
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.daily_post_fragment_layout.*

@AndroidEntryPoint
class DailyPostFragment : Fragment() {

    private lateinit var currentView: View
    private val viewModel: DailyPostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentView = inflater.inflate(R.layout.daily_post_fragment_layout, container, false)
        subscribeObservers()
        viewModel.setStateEvent(StateEvent.GetDailyPost)
        return currentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_daily.setOnRefreshListener {
            viewModel.setStateEvent(StateEvent.GetDailyPost)
        }
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            Log.d(TAG, "subscribeObservers: $dataState")
            when (dataState) {
                is DataState.Success<DailyPost> -> {
                    displayProgress(false)
                    Log.d(TAG, "subscribeObservers: ${dataState.data}")
                    bindData(dataState.data)
                }
                is DataState.Error -> {
                    //displayProgress(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgress(true)
                }
            }
        })
    }


    private fun displayError(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


    private fun displayProgress(isLoading: Boolean) {
        swipe_refresh_daily.isRefreshing = isLoading
        data_container.visibility = if (isLoading) View.GONE else View.VISIBLE
    }

    private fun bindData(dailyPost: DailyPost) {
        daily_post_title.text = dailyPost.title
        daily_post_explanation.text = dailyPost.explanation
        Glide.with(this)
            .load(dailyPost.hdUrl)
            .centerCrop()
            .placeholder(R.drawable.nasa_logo)
            .into(daily_post_image)
    }

    companion object {
        const val TAG = "DailyPostFragment"
    }

}