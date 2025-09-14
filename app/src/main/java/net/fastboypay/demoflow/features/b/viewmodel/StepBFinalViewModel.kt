package net.fastboypay.demoflow.features.b.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import net.fastboypay.demoflow.data.model.BData
import net.fastboypay.demoflow.base.viewmodel.BaseViewModel
import com.google.gson.Gson
import javax.inject.Inject

@HiltViewModel
class StepBFinalViewModel @Inject constructor(
    private val gson: Gson
) : BaseViewModel() {
    
    private val _bData = createStateFlow(BData("", "", ""))
    val bData: StateFlow<BData> = _bData.asReadOnlyStateFlow()
    
    private val _isFull = createStateFlow(false)
    val isFull: StateFlow<Boolean> = _isFull.asReadOnlyStateFlow()
    
    fun initializeData(initialData: BData, initialIsFull: Boolean = false) {
        _bData.updateValue(initialData)
        _isFull.updateValue(initialIsFull)
    }
    
    fun getFinalData(): BData {
        return _bData.value
    }
    
    fun getDataAsJson(): String {
        return gson.toJson(getFinalData())
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
