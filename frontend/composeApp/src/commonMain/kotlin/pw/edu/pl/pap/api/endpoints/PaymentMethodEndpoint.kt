package pw.edu.pl.pap.api.endpoints

open class PaymentMethodEndpoint(relativePath: String) : BaseEndpoint("/method", relativePath) {
    data object AllMethods : PaymentMethodEndpoint("/all")
}