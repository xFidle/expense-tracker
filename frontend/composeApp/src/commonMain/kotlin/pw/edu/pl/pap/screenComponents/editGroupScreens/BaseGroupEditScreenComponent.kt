package pw.edu.pl.pap.screenComponents.editGroupScreens

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import pw.edu.pl.pap.data.uiSetup.inputFields.DatePickerData
import pw.edu.pl.pap.data.uiSetup.inputFields.DropdownListData
import pw.edu.pl.pap.data.uiSetup.inputFields.InputFieldData
import pw.edu.pl.pap.data.uiSetup.inputFields.TextFieldData
import pw.edu.pl.pap.screenComponents.mainScreens.BaseScreenComponent
import pw.edu.pl.pap.util.sanitizePriceInput
import pw.edu.pl.pap.util.updatePrice

open class BaseGroupEditScreenComponent(
    baseComponent: BaseScreenComponent,
    val onDismiss: () -> Unit,
    val onSave: () -> Unit
) : BaseScreenComponent by baseComponent {

    private val _inputFieldsData = mutableStateListOf<InputFieldData>()
    val inputFieldsData: List<InputFieldData> get() = _inputFieldsData

    protected open var name: MutableState<String> = mutableStateOf("")

    val canConfirm by derivedStateOf { name.value.isNotEmpty() }

    fun setupInputFields() {
        _inputFieldsData.clear()
        _inputFieldsData.addAll(
            listOf(
                InputFieldData(
                    title = "Name: ",
                    textFieldData = TextFieldData(
                        parameter = name,
                        onChange = {
                            coroutineScope.launch { name.value = it }
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
