package pw.edu.pl.pap.api.auth

import de.jensklingenberg.ktorfit.Ktorfit
import pw.edu.pl.pap.api.ServiceCreator

class AuthServiceCreator(baseUrl: String) : ServiceCreator() {
    private val ktorfit = Ktorfit.Builder().baseUrl(baseUrl).httpClient(httpClient).build()


    fun createLoginApi(): LoginApi {
        return ktorfit.createLoginApi()
    }

    fun createSignupApi(): SignUpApi {
        return ktorfit.createSignUpApi()
    }
}