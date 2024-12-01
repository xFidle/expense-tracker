package pw.edu.pl.pap.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.utils.io.core.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pw.edu.pl.pap.apiclient.NewExpenseApiClient
import pw.edu.pl.pap.data.InputFieldData
import pw.edu.pl.pap.data.Record
import pw.edu.pl.pap.data.User

class NewExpenseViewModel(private val apiClient: NewExpenseApiClient) : ViewModel() {
    private val _inputFieldsData: MutableList<InputFieldData> = mutableListOf()
    val inputFieldsData: List<InputFieldData> = _inputFieldsData.toList()

    var record = Record(0, 0.toFloat(), User(0, "", "", ""))

    fun setupInputFieldsData() {
        _inputFieldsData.add(
            InputFieldData(
            title = "Price: ",
            parameter = record.price.toString(),
                onChange = { newParameter ->
                     record = Record(0, newParameter.toFloat(), User(0, "", "", ""))
                }
            )
        )
    }
}