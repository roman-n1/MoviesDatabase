package com.roman.yarullin.moviesdatabase.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<ViewState : BaseViewState, ViewAction : BaseAction>(initialState: ViewState) : ViewModel()  {

    protected val stateMutableLiveData = MutableLiveData<ViewState>(initialState)
    val stateLiveData = stateMutableLiveData as LiveData<ViewState>

    val state: ViewState
        get() {
            return stateMutableLiveData.value!!
        }

    fun sendAction(viewAction: ViewAction) {
        stateMutableLiveData.value = onReduceState(viewAction)
    }

    abstract fun onReduceState(viewAction: ViewAction): ViewState
}