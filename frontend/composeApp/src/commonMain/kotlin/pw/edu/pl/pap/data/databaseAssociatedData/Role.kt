package pw.edu.pl.pap.data.databaseAssociatedData


import kotlinx.serialization.Serializable

@Serializable
data class Role(
    val id: Long,
    val name: String,
)