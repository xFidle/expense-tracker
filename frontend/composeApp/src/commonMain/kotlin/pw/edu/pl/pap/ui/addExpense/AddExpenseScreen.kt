package pw.edu.pl.pap.ui.addExpense

import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text

@Composable
fun AddExpenseScreen(onClose: () -> Unit){
    println("add_expense_screen")
    Button(onClick = { onClose() }) {Text("gfd")}

}