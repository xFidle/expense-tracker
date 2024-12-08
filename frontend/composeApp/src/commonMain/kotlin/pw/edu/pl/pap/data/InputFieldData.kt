package pw.edu.pl.pap.data

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.MutableState
import kotlinx.serialization.Serializable

data class InputFieldData(
    val title: String,
    val isDropdownList: Boolean,

    // fields used for TextFields
    var parameter: MutableState<String>? = null,
    val onChange: ((String) -> Unit)? = null,
    val keyboardOptions: KeyboardOptions? = null,  // Optional keyboard options, used for number only keyboard

    //fields used for DropdownLists
    val itemList: List<String>? = null,
    val selectedIndex: MutableState<Int>? = null,
    val onItemClick: ((Int) -> Unit)? = null
)