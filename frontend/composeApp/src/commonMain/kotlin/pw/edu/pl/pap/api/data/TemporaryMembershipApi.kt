package pw.edu.pl.pap.api.data

import de.jensklingenberg.ktorfit.http.*
import io.ktor.client.statement.*
import pw.edu.pl.pap.data.databaseAssociatedData.User
import pw.edu.pl.pap.data.databaseAssociatedData.UserGroup

interface TemporaryMembershipApi {

    @POST("tempMembership/accept/{id}")
    suspend fun accept(@Path("id")  id: Int): HttpResponse

    @DELETE("tempMembership/delete/{id}")
    suspend fun decline(@Path("id")  id: Int): HttpResponse

    @GET("tempMembership/invitations")
    suspend fun getReceived(): List<Invitation>

    @GET("tempMembership/invitations")
    suspend fun getSent(): List<Invitation>
    //TODO correct endpoint

    //    data class Accept(val id: Long): TemporaryMembershipEndpoint("/accept/$id")
    //    data class Decline(val id: Long): TemporaryMembershipEndpoint("/delete/$id")
    //    data object GetReceived: TemporaryMembershipEndpoint("/invitations")
    //    data object GetSent: TemporaryMembershipEndpoint("/invitations")

}