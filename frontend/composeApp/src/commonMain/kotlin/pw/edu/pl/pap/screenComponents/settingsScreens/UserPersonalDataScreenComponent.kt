package pw.edu.pl.pap.screenComponents.settingsScreens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pw.edu.pl.pap.data.uiSetup.ConfirmationDialogConfig
import pw.edu.pl.pap.data.uiSetup.inputFields.InputFieldData
import pw.edu.pl.pap.data.uiSetup.inputFields.TextFieldData
import pw.edu.pl.pap.screenComponents.mainScreens.BaseScreenComponent

class UserPersonalDataScreenComponent (
    val onBack: () -> Unit,
    baseComponent: BaseScreenComponent
) : BaseScreenComponent by baseComponent {

    private val _inputFieldsData = mutableStateListOf<InputFieldData>()
    val inputFieldsData: List<InputFieldData> get() = _inputFieldsData

    private var email: MutableState<String> = mutableStateOf("")
    private var name: MutableState<String> = mutableStateOf("")
    private var surname: MutableState<String> = mutableStateOf("")

    var showConfirmationDialog: MutableState<Boolean> = mutableStateOf(false)
    val confirmationData = ConfirmationDialogConfig(
        mainText = "Change Personal Data",
        subText = "Are you sure you want to change your personals?",
        onNo = { showConfirmationDialog.value = false },
        onYes = {
            showConfirmationDialog.value = false
            coroutineScope.launch { saveNewUserPersonals() }
            onBack()
        }
    )

    private fun saveNewUserPersonals() {
        //TODO
    }

    private fun fetchUserData() {
        runBlocking {
            //TODO
        }
    }

    fun setupInputFields() {
        _inputFieldsData.clear()
        fetchUserData()
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
                    title = "Name",
                    textFieldData = TextFieldData(
                        parameter = name,
                        onChange = {
                            coroutineScope.launch { name.value = it }
                        },
                    )
                ),
                InputFieldData(
                    title = "Surname",
                    textFieldData = TextFieldData(
                        parameter = surname,
                        onChange = {
                            coroutineScope.launch { surname.value = it }
                        },
                    )
                )
            )
        )
    }


}