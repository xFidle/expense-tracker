package pw.edu.pl.pap.data.databaseAssociatedData

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Membership(
    val id: Long,
    val user: User,
    val group: Group,
    val name: String,
    val role: Role,
)