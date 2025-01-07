package pw.edu.pl.pap.api

import io.ktor.client.call.*
import pw.edu.pl.pap.api.endpoints.PaymentMethodEndpoint
import pw.edu.pl.pap.data.databaseAssociatedData.PaymentMethod

class PaymentMethodApiClient(baseApiClient: BaseApiClient) :
    ApiClient by baseApiClient {

    suspend fun getMethods(): List<PaymentMethod> {
        return get(PaymentMethodEndpoint.AllMethods).body()
    }
}