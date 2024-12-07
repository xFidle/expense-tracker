package pw.edu.pl.pap.ui.addExpense

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import pw.edu.pl.pap.navigation.NewExpenseScreenComponent
import pw.edu.pl.pap.ui.common.Header

@Composable
fun NewExpenseScreen(
    component: NewExpenseScreenComponent
    ){
    val scope = rememberCoroutineScope()

    Header("New expense")
    component.setupInputFields()
    InputFields(component.inputFieldsData)

    AddButton(onUpdate = {
        scope.launch{
            component.expenseConfirmed(onConfirm = component.onBack)
        }
    })
    BackButton(onUpdate = {
        scope.launch {
            component.onBack()
        }
    })

}