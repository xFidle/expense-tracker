package pw.edu.pl.pap.screenComponents.chartsScreens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import pw.edu.pl.pap.data.databaseAssociatedData.*
import pw.edu.pl.pap.data.uiSetup.inputFields.InputFieldData
import pw.edu.pl.pap.screenComponents.BaseScreenComponent
import kotlin.reflect.KProperty1

class ChartsFilterScreenComponent(
    baseComponent: BaseScreenComponent,
    val onDismiss: () -> Unit,
    private val onConfirm: (ChartFilterParams, String) -> Unit,
    private val filterParams: ChartFilterParams,
    private val currentGroup: UserGroup,
    currentKeyPattern: String
) : BaseScreenComponent by baseComponent {

    private val _inputFieldsData = mutableStateListOf<InputFieldData>()
    val inputFieldsData: SnapshotStateList<InputFieldData> get() = _inputFieldsData

    private lateinit var users: List<User>
    private lateinit var methods: List<PaymentMethod>
    private lateinit var categories: List<Category>
    private lateinit var patternKeys: List<String>

    private var selectedCategories: SnapshotStateList<Int>? = null
    private var selectedEmails: SnapshotStateList<Int>? = null
    private var selectedMethods: SnapshotStateList<Int>? = null
    private lateinit var selectedKeyPattern: MutableState<Int>


}