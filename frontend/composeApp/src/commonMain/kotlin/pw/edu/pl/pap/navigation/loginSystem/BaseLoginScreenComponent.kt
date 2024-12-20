package pw.edu.pl.pap.navigation.loginSystem

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import pw.edu.pl.pap.api.ApiService
import pw.edu.pl.pap.data.inputFields.DatePickerData
import pw.edu.pl.pap.data.inputFields.DropdownListData
import pw.edu.pl.pap.data.inputFields.InputFieldData
import pw.edu.pl.pap.data.inputFields.TextFieldData
import pw.edu.pl.pap.util.sanitizePriceInput
import pw.edu.pl.pap.util.updatePrice

open class BaseLoginScreenComponent(
    componentContext: ComponentContext,
    protected val apiService: ApiService,
    protected val coroutineScope: CoroutineScope,
    val onConfirm: () -> Unit
) : ComponentContext by componentContext {
    private val _inputFieldsData = mutableStateListOf<InputFieldData>()
    val inputFieldsData: List<InputFieldData> get() = _inputFieldsData

    protected open var email: MutableState<String> = mutableStateOf("")

    protected open var password: MutableState<String> = mutableStateOf("")

    fun setupLoginInputFields() {
        _inputFieldsData.clear()
        _inputFieldsData.addAll(
            listOf(
                InputFieldData(
                    title = "Email: ",
                    textFieldData = TextFieldData(
                        parameter = email,
                        onChange = {
                            coroutineScope.launch { email.value = it }
                        }
                    )
                ),
                InputFieldData(
                    title = "Password",
                    textFieldData = TextFieldData(
                        parameter = password,
                        onChange = {
                            coroutineScope.launch { password.value = it }
                        }
                    )
                )
            )
        )
    }

    open fun confirm() {
        throw NotImplementedError("Subclasses must override confirm")
    }
}