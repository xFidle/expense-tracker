package pw.edu.pl.pap.ui.loginSystem

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.edu.pl.pap.screenComponents.loginSystem.SelectionLoginSignupScreenComponent
import pw.edu.pl.pap.ui.common.TextButton

@Composable
fun LogInSignUpSelectionScreen(
    component: SelectionLoginSignupScreenComponent
) {
    val loading by component.loading.collectAsState()

    LaunchedEffect(Unit) {
        component.checkForToken()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
        } else {
            Column(
                modifier = Modifier
            ) {
                TextButton(
                    text = "LOG IN",
                    modifier = Modifier.offset(x = 0.dp, y = (-20).dp),
                    onUpdate = component.onLogInButtonClicked
                )
                TextButton(
                    text = "SIGN UP",
                    modifier = Modifier.offset(x = 0.dp, y = 20.dp),
                    onUpdate = component.onSignupButtonClicked
                )
            }
        }
    }
}