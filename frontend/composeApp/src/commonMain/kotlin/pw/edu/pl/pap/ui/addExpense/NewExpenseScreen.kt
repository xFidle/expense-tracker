package pw.edu.pl.pap.ui.addExpense

import androidx.compose.runtime.*

@Composable
fun NewExpenseScreen(onClose: () -> Unit){
    var addButtonClicked by remember { mutableStateOf(false) }

    Header()

    AddButton(addButtonClicked, onUpdate = {addButtonClicked = !addButtonClicked})


    if (addButtonClicked) {
        //add to database
        onClose()
    }
}