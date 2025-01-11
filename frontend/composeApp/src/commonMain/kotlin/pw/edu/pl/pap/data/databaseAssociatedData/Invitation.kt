package pw.edu.pl.pap.data.databaseAssociatedData

import kotlinx.serialization.Serializable

@Serializable
data class Invitation(
    val id: Long,
    val user: User,
    val group: UserGroup
)