package pw.edu.pl.pap.data.databaseAssociatedData

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Group(
    val id: Long,
    val name: String,
)