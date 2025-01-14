package pw.edu.pl.pap.api.auth

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.POST
import io.ktor.client.statement.*
import pw.edu.pl.pap.data.databaseAssociatedData.RefreshToken

interface RefreshTokenApi {
    @POST("auth/refresh")
    suspend fun refresh(@Body refreshToken: RefreshToken): HttpResponse
}