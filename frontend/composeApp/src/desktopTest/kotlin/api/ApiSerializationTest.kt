package api

import io.ktor.client.call.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import pw.edu.pl.pap.data.databaseAssociatedData.User
import kotlin.test.*

class ApiSerializationTest {

    @Test
    fun `test deserialization of user data`() {
        val mockResponse = """
            {
                "id": 1,
                "name": "Jurek",
                "surname": "Ogórek",
                "email": "jurekogorek@gmail.com"
            }
        """.trimIndent()

        val mockEngine = MockEngine { request ->
            respond(
                content = mockResponse,
                status = HttpStatusCode.OK,
                headers = headersOf("Content-Type", "application/json")
            )
        }

        val client = createMockClient(mockEngine)

        runBlocking {
            val user: User = client.get("https://api.example.com/user/1").body()
            assertEquals(1, user.id)
            assertEquals("Jurek", user.name)
            assertEquals("Ogórek", user.surname)
            assertEquals("jurekogorek@gmail.com", user.email)
        }
    }
}