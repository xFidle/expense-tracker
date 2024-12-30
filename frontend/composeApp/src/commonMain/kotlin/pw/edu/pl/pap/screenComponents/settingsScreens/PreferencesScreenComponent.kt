package pw.edu.pl.pap.screenComponents.settingsScreens

import androidx.compose.runtime.mutableStateListOf
import pw.edu.pl.pap.data.uiSetup.inputFields.InputFieldData
import pw.edu.pl.pap.screenComponents.mainScreens.BaseScreenComponent

class PreferencesScreenComponent (
    baseComponent: BaseScreenComponent
) : BaseScreenComponent by baseComponent {

    private val _inputFieldsData = mutableStateListOf<InputFieldData>()
    val inputFieldsData: List<InputFieldData> get() = _inputFieldsData
}