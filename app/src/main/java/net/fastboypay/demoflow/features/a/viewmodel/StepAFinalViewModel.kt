package net.fastboypay.demoflow.features.a.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import net.fastboypay.demoflow.data.model.AData
import net.fastboypay.demoflow.base.viewmodel.BaseViewModel
import com.google.gson.Gson
import javax.inject.Inject

@HiltViewModel
class StepAFinalViewModel @Inject constructor(
    private val gson: Gson
) : BaseViewModel() {
    
    private val _aData = createStateFlow(AData())
    val aData: StateFlow<AData> = _aData.asReadOnlyStateFlow()
    
    
    fun initializeData(initialData: AData) {
        _aData.updateValue(initialData)
    }
    
    fun getFinalData(): AData {
        return _aData.value
    }
    
    fun getDataAsJson(): String {
        return gson.toJson(getFinalData())
    }
    
    fun initializeFromJson(jsonData: String) {
        try {
            val data = gson.fromJson(jsonData, AData::class.java) ?: AData()
            initializeData(data)
        } catch (e: Exception) {
            initializeData(AData())
        }
    }
}
