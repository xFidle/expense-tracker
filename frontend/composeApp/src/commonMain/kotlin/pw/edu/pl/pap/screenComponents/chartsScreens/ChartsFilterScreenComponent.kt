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

    init {
        coroutineScope.launch {
            val categoriesCor = async { apiService.categoryApiClient.getCategories() }
            val usersCor = async { apiService.groupApiClient.getUsersInGroup(currentGroup.name) }
            val methodsCor = async { apiService.paymentMethodApiClient.getMethods() }
            try {
                categories = categoriesCor.await()
                users = usersCor.await()
                methods = methodsCor.await()
                patternKeys = listOf("category", "user", "method") //TODO add api request for keys

                selectedCategories =
                    getIndicesFromItems(categories, filterParams.categoryNames, Category::name)?.toMutableStateList()
                selectedEmails = getIndicesFromItems(users, filterParams.emails, User::email)?.toMutableStateList()
                selectedMethods =
                    getIndicesFromItems(methods, filterParams.methods, PaymentMethod::name)?.toMutableStateList()
                selectedKeyPattern = mutableStateOf(patternKeys.indexOf(currentKeyPattern))

                initializeInputFieldsData()
            } catch (e: Exception) {
                println("Error fetching data: ${e.message}")
            }
        }
    }

    private fun initializeInputFieldsData() {
        _inputFieldsData.clear()
        _inputFieldsData.addAll(
            listOf(
                InputFieldData.DropdownListData(
                    title = "Group by",
                    itemList = patternKeys,
                    selectedIndex = selectedKeyPattern,
                    onItemClick = { selectedKeyPattern.value = it }),
                InputFieldData.CheckboxData(
                    title = "Categories",
                    itemList = categories.map { it.name },
                    selectedIndices = selectedCategories,
                    onConfirm = { selectedCategories = it?.toMutableStateList() }),
                InputFieldData.CheckboxData(
                    title = "Users",
                    itemList = users.map { "${it.name} ${it.surname}" },
                    selectedIndices = selectedEmails,
                    onConfirm = { selectedEmails = it?.toMutableStateList() }),
                InputFieldData.CheckboxData(
                    title = "Payment methods",
                    itemList = methods.map { it.name },
                    selectedIndices = selectedMethods,
                    onConfirm = { selectedMethods = it?.toMutableStateList() })
            )
        )
    }

    private fun <T : Any> getIndicesFromItems(
        dataList: List<T>, items: List<String>?, field: KProperty1<T, *>
    ): List<Int>? {
        return items?.let {
            dataList.mapIndexedNotNull { index, item ->
                val fieldValue = field.get(item) as? String
                if (fieldValue in items) index else null
            }
        }
    }

}