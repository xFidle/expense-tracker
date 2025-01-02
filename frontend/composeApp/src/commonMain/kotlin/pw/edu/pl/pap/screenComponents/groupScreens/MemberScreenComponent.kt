package pw.edu.pl.pap.screenComponents.groupScreens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.launch
import pw.edu.pl.pap.data.databaseAssociatedData.User
import pw.edu.pl.pap.data.databaseAssociatedData.UserGroup
import pw.edu.pl.pap.data.uiSetup.inputFields.ButtonData
import pw.edu.pl.pap.data.uiSetup.inputFields.DropdownListData
import pw.edu.pl.pap.data.uiSetup.inputFields.InputFieldData
import pw.edu.pl.pap.data.uiSetup.inputFields.TextFieldData
import pw.edu.pl.pap.screenComponents.mainScreens.BaseScreenComponent

class MemberScreenComponent (
    baseComponent: BaseScreenComponent,
    val user: User,
    private val currentUserGroup: UserGroup,
    val onBack: () -> Unit
) : BaseScreenComponent by baseComponent {

    private val _inputFieldsData = mutableStateListOf<InputFieldData>()
    val inputFieldsData: List<InputFieldData> get() = _inputFieldsData

    private val roles = listOf("admin", "viewer")
    protected var initialIndex: MutableState<Int> = mutableStateOf(0)
    protected var roleIndex: MutableState<Int> = mutableStateOf(0)

    var showChangeRoleConfirmationDialog: MutableState<Boolean> = mutableStateOf(false)
    var showKickConfirmationDialog: MutableState<Boolean> = mutableStateOf(false)



    fun setupInputFields() {
        _inputFieldsData.clear()
        _inputFieldsData.addAll(
            listOf(
                InputFieldData(
                    title = "Role: ",
                    isDropdownList = true,
                    dropdownListData = DropdownListData(
                        itemList = roles,
                        selectedIndex = roleIndex,
                        onItemClick = {
                            coroutineScope.launch { roleIndex.value = it }
                        }
                    )
                ),
                InputFieldData(
                    title = "",
                    isButton = true,
                    buttonData = ButtonData(
                        title = "KICK",
                        onClick = {
                            coroutineScope.launch { showKickConfirmationDialog.value = true }
                        }
                    )
                )
            )
        )
    }

    private fun changeRole() {
        //TODO
    }
}