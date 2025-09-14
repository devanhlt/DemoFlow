package net.fastboypay.demoflow.features.b.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import net.fastboypay.demoflow.data.model.BData
import net.fastboypay.demoflow.base.viewmodel.BaseViewModel
import com.google.gson.Gson
import javax.inject.Inject

@HiltViewModel
class StepB2ViewModel @Inject constructor(
    private val gson: Gson
) : BaseViewModel() {
    
    private val _bData = createStateFlow(BData("", "", ""))
    private val _b2Text = createStateFlow("")
    val b2Text: StateFlow<String> = _b2Text.asReadOnlyStateFlow()
    
    
    private val _isFull = createStateFlow(false)
    val isFull: StateFlow<Boolean> = _isFull.asReadOnlyStateFlow()
    
    fun initializeData(initialData: BData, initialIsFull: Boolean = false) {
        _bData.updateValue(initialData)
        _b2Text.updateValue(initialData.b2)
        _isFull.updateValue(initialIsFull)
    }
    
    
    fun updateB2Text(text: String) {
        _b2Text.updateValue(text)
    }
    
    
    fun getUpdatedData(): BData {
        return _bData.value.copy(b2 = _b2Text.value)
    }
    
    fun getDataAsJson(): String {
        return gson.toJson(getUpdatedData())
    }
    
    fun initializeFromJson(jsonData: String) {
        try {
            val data = gson.fromJson(jsonData, BData::class.java) ?: BData()
            initializeData(data, _isFull.value)
        } catch (e: Exception) {
            initializeData(BData(), false)
        }
    }
}
