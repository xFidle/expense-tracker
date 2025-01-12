package pw.edu.pl.pap.repositories.auth

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import pw.edu.pl.pap.api.auth.LoginApi
import pw.edu.pl.pap.api.auth.SignUpApi
import pw.edu.pl.pap.data.databaseAssociatedData.Tokens
import pw.edu.pl.pap.data.databaseAssociatedData.UserLoginData
import pw.edu.pl.pap.data.databaseAssociatedData.UserSignUpData

class SignupRepository(private val signUpApi: SignUpApi, private val tokenRepository: TokenRepository) {
    suspend fun signup(userSignUpData: UserSignUpData): Boolean {
        val response = signUpApi.signUp(userSignUpData)

        println(response)
        println("HTTP Status: ${response.status}")
        println("Headers: ${response.headers}")
        println("Body: ${response.bodyAsText()}")
        println("here")
        //TODO remove when debugged

        //TODO add different return based on error
        if (!response.status.isSuccess()) {
            return false
        }

        tokenRepository.setTokens(response.body<Tokens>())
        return true
    }
}