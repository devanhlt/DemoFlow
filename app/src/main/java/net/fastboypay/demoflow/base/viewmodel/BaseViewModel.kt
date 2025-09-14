package net.fastboypay.demoflow.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel : ViewModel() {
    
    protected fun <T> MutableStateFlow<T>.updateValue(newValue: T) {
        value = newValue
    }
    
    protected fun <T> createStateFlow(initialValue: T): MutableStateFlow<T> {
        return MutableStateFlow(initialValue)
    }
    
    protected fun <T> MutableStateFlow<T>.asReadOnlyStateFlow(): StateFlow<T> {
        return asStateFlow()
    }
}
