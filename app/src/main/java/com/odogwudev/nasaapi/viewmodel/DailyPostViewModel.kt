package com.odogwudev.nasaapi.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.odogwudev.nasaapi.model.DailyPost
import com.odogwudev.nasaapi.utils.DataState
import com.odogwudev.nasaapi.repository.MainRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DailyPostViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<DailyPost>> = MutableLiveData()

    val dataState: LiveData<DataState<DailyPost>>
        get() = _dataState

    fun setStateEvent(stateEvent: StateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                StateEvent.GetDailyPost -> {
                    mainRepository.getDailyPost()
                        .onEach {
                            _dataState.value = it
                        }
                        .launchIn(viewModelScope)
                }

                StateEvent.None -> {
                    // who cares
                }
            }
        }
    }

}

sealed class StateEvent {
    object GetDailyPost : StateEvent()
    object None : StateEvent()
}