package net.fastboypay.demoflow.features.a.viewmodel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import net.fastboypay.demoflow.data.model.AData
import net.fastboypay.demoflow.base.viewmodel.BaseViewModel
import com.google.gson.Gson
import javax.inject.Inject

@HiltViewModel
class StepA4ViewModel @Inject constructor(
    private val gson: Gson
) : BaseViewModel() {
    
    private val _aData = createStateFlow(AData())
    private val _a4Text = createStateFlow("")
    val a4Text: StateFlow<String> = _a4Text.asReadOnlyStateFlow()
    
    fun initializeData(initialData: AData) {
        _aData.updateValue(initialData)
        _a4Text.updateValue(initialData.a4)
    }
    
    fun updateA4Text(text: String) {
        _a4Text.updateValue(text)
    }
    
    fun getUpdatedData(): AData {
        return _aData.value.copy(a4 = _a4Text.value)
    }
    
    fun getDataAsJson(): String {
        return gson.toJson(getUpdatedData())
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
