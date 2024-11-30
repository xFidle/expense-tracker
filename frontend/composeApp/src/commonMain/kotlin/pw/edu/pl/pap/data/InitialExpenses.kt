package pw.edu.pl.pap.data

import kotlinx.serialization.Serializable


@Serializable
data class InitialExpenses(
    val userExpenses: Double? = null,
    val expenses: Double? = null,
)