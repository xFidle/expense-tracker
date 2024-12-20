package pw.edu.pl.pap.ui.loginSystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import pw.edu.pl.pap.navigation.loginSystem.LoginScreenComponent
import pw.edu.pl.pap.ui.common.Header
import pw.edu.pl.pap.ui.common.InputFields
import pw.edu.pl.pap.ui.common.TextButton

@Composable
fun LogInScreen (
    component: LoginScreenComponent
) {
    val scope = rememberCoroutineScope()

    Header("Log In")
    component.setupInputFields()
    InputFields(component.inputFieldsData)


    TextButton(
        text = "LOG IN",
        modifier = Modifier.align(Alignment.BottomCenter),
        onUpdate = { scope.launch { component.confirm() } }
    )
}