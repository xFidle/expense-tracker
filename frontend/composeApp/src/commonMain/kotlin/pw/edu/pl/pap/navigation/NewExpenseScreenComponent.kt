package pw.edu.pl.pap.navigation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.KeyboardType
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import pw.edu.pl.pap.apiclient.ApiClient
import pw.edu.pl.pap.data.inputFields.InputFieldData
import pw.edu.pl.pap.data.NewExpense
import pw.edu.pl.pap.data.User
import pw.edu.pl.pap.data.inputFields.TextFieldData
import pw.edu.pl.pap.util.sanitizePriceInput
import pw.edu.pl.pap.util.updatePrice
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import pw.edu.pl.pap.data.inputFields.DropdownListData

class NewExpenseScreenComponent (
    componentContext: ComponentContext,
    private val apiClient: ApiClient,
    private val coroutineScope: CoroutineScope,
    val onBack: () -> Unit
) : ComponentContext by componentContext {

    private val _inputFieldsData = mutableStateListOf<InputFieldData>() // Backing mutable state
    val inputFieldsData: List<InputFieldData> get() = _inputFieldsData // Immutable public view


    private var price: MutableState<String> = mutableStateOf("")

    private var selectedIndex: MutableState<Int> = mutableStateOf(0)

//    private var price by remember { mutableStateOf("")}




    fun setupInputFields() {
        _inputFieldsData.addAll(
            listOf(
                InputFieldData(
                    title = "Price: ",
                    isDropdownList = false,
                    textFieldData = TextFieldData(
                        parameter = price,
                        onChange = { newParameter ->
                            val sanitizedInput = sanitizePriceInput(newParameter)
                            if (sanitizedInput != null) {
                                coroutineScope.launch { updatePrice(sanitizedInput, price) }
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                ),
                InputFieldData(
                    title = "Currency",
                    isDropdownList = true,
                    dropdownListData = DropdownListData(
                        itemList = listOf("PLN", "EUR", "USD"),
                        selectedIndex = selectedIndex,
                        onItemClick = {
                            selectedIndex.value = it
                        }
                    )
                )
            )
        )
    }




    fun expenseConfirmed(onConfirm: () -> Unit) {
        // download to set ID
        // find user
        // save expense
//        val id: Long = 5
        val user: User = User(1, "Marcinek", "Marcinkowski", "Kaczka2137@gmail.com")
        val date: LocalDate = Clock.System.todayIn(TimeZone.UTC)
//        val category: Category = Category(5, "Test")
//        val expense: Expense = Expense(id, price.value.toFloat(), user, date, category )
        val newExpense: NewExpense = NewExpense(price.value.toFloat(), date, user)

        coroutineScope.launch{
            apiClient.postNewExpense(newExpense)
            onConfirm()
        }

        println("confirmed " + newExpense.price)
    }

}