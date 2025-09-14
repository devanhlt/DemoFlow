package net.fastboypay.demoflow.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import net.fastboypay.demoflow.data.preferences.AppPreferences
import net.fastboypay.demoflow.data.model.EntryStep
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appPreferences: AppPreferences
) : ViewModel() {

    private val _isFull = MutableStateFlow(appPreferences.isFull)
    val isFull: StateFlow<Boolean> = _isFull.asStateFlow()

    private val _selectedEntryStep = MutableStateFlow(EntryStep.A1)
    val selectedEntryStep: StateFlow<EntryStep> = _selectedEntryStep.asStateFlow()

    fun updateIsFull(newValue: Boolean) {
        _isFull.value = newValue
        appPreferences.isFull = newValue
    }

    fun updateSelectedEntryStep(entryStep: EntryStep) {
        _selectedEntryStep.value = entryStep
    }

    fun createDataFromInputs(entryStep: EntryStep, inputs: Map<String, String>): String {
        return when (entryStep) {
            EntryStep.A1, EntryStep.A2, EntryStep.A3, EntryStep.A4, EntryStep.AFinal -> {
                val aData = net.fastboypay.demoflow.data.model.AData(
                    a1 = inputs["A1"] ?: "",
                    a2 = inputs["A2"] ?: "",
                    a3 = inputs["A3"] ?: "",
                    a4 = inputs["A4"] ?: "",
                )
                com.google.gson.Gson().toJson(aData)
            }

            EntryStep.B1, EntryStep.B2, EntryStep.B3, EntryStep.BFinal -> {
                val bData = net.fastboypay.demoflow.data.model.BData(
                    b1 = inputs["B1"] ?: "",
                    b2 = inputs["B2"] ?: "",
                    b3 = inputs["B3"] ?: ""
                )
                com.google.gson.Gson().toJson(bData)
            }
        }
    }
}
