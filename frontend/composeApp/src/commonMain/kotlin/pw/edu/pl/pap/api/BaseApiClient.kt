package pw.edu.pl.pap.api

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

open class BaseApiClient(
    private val baseUrl: String,
    private val httpClient: HttpClient
) {
    suspend fun get(endpoint: String): HttpResponse {
        return httpClient.get("$baseUrl$endpoint")
    }

    suspend fun post(endpoint: String, body: Any): HttpResponse {
        return httpClient.post("$baseUrl$endpoint") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }

    suspend fun put(endpoint: String, body: Any): HttpResponse {
        return httpClient.put("$baseUrl$endpoint") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
    }

    suspend fun delete(endpoint: String): HttpResponse {
        return httpClient.delete("$baseUrl$endpoint")
    }
}
