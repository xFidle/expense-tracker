package pw.edu.pl.pap.screenComponents.mainScreens

import androidx.compose.runtime.mutableStateListOf
import pw.edu.pl.pap.data.databaseAssociatedData.User
import pw.edu.pl.pap.data.databaseAssociatedData.UserGroup
import pw.edu.pl.pap.data.uiSetup.inputFields.InputFieldData

class GroupScreenComponent(
    private val onUserClicked: (User) -> Unit,
    private val currentUserGroup: UserGroup,
    baseComponent: BaseScreenComponent,
) : BaseScreenComponent by baseComponent {

    private val _inputFieldsData = mutableStateListOf<InputFieldData>()
    val inputFieldsData: List<InputFieldData> get() = _inputFieldsData

    fun setupInputFields() {

    }

}