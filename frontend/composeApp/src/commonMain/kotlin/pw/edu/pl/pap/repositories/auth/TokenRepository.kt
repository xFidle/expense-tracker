package pw.edu.pl.pap.repositories.auth

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TokenRepository {
    private val _token = MutableStateFlow<String?>(null)
    val token: StateFlow<String?> get() = _token

    fun setToken(newToken: String) {
        _token.value = newToken
    }

    fun getToken(): String = _token.value!!
}