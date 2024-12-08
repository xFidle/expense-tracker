package pw.edu.pl.pap.data.inputFields

data class InputFieldData(
    val title: String,
    val isDropdownList: Boolean,

    val textFieldData: TextFieldData? = null,
    val dropdownListData: DropdownListData? = null
)