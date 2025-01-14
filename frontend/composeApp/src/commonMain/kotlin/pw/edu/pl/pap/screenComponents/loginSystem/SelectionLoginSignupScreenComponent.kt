package pw.edu.pl.pap.screenComponents.loginSystem

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pw.edu.pl.pap.repositories.auth.TokenRepository

class SelectionLoginSignupScreenComponent(
    componentContext: ComponentContext,
    val onLogInButtonClicked: () -> Unit,
    val onSignupButtonClicked: () -> Unit,
    val onTokenFound: () -> Unit,
//    private val tokenRepository: TokenRepository
) : ComponentContext by componentContext, KoinComponent {

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading

    suspend fun checkForToken() {
        _loading.value = true
        val tokenRepository: TokenRepository by inject()
        val valid = tokenRepository.checkRefreshToken()
        if (valid) {
            onTokenFound()
        }
        _loading.value = false
    }

}