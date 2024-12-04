package pw.edu.pl.pap.ui.addExpense

import androidx.compose.runtime.*
import kotlinx.coroutines.launch
import pw.edu.pl.pap.viewmodel.NewExpenseViewModel

@Composable
fun NewExpenseScreen(
    viewModel: NewExpenseViewModel,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
    ){
    var addButtonClicked by remember { mutableStateOf(false) }
    var backButtonClicked by remember { mutableStateOf(false) }

    Header()
    viewModel.setupInputFields()
    InputFields(viewModel.inputFieldsData)

    AddButton(addButtonClicked, onUpdate = {addButtonClicked = !addButtonClicked})
    BackButton(backButtonClicked, onUpdate = {backButtonClicked = !backButtonClicked})


    if (addButtonClicked) {
        viewModel.expenseConfirmed(onConfirm = onConfirm)
    }

    if (backButtonClicked) {
        rememberCoroutineScope().launch {
            onCancel()
        }
    }
}