package pw.edu.pl.pap.navigation.loginSystem

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import pw.edu.pl.pap.api.ApiService
import pw.edu.pl.pap.data.Expense

class SelectionLoginSignupScreenComponent(
    componentContext: ComponentContext,
    val onLogInButtonClicked: () -> Unit,
    val onSignupButtonClicked: () -> Unit
) : ComponentContext by componentContext {
    //TODO show log in button
    //TODO show Sign up button
}