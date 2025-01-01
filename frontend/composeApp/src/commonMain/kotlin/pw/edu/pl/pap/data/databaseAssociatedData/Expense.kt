package pw.edu.pl.pap.data.databaseAssociatedData

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDate

@Serializable
data class Expense(
    val id: Long,
    val title: String,
    val price: Float,
    val membership: Membership,
    val date: LocalDate,
    val category: Category,
    val method: String,
    val currency: Currency,
)
