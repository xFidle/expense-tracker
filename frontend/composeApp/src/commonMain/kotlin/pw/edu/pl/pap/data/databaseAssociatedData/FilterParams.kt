package pw.edu.pl.pap.data.databaseAssociatedData

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

sealed class FilterParams() {
    abstract fun toMap(): Map<String, String>
}

@Serializable
data class ChartFilterParams(
    val beginDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val categoryNames: List<String>? = null,
    val emails: List<String>? = null,
    val methods: List<String>? = null
) : FilterParams() {
    override fun toMap(): Map<String, String> {
        return mapOf(
            "beginDate" to beginDate?.toString().orEmpty(),
            "endDate" to endDate?.toString().orEmpty(),
            "categoryNames" to categoryNames?.toString().orEmpty(),
            "emails" to emails?.toString().orEmpty(),
            "methods" to methods?.toString().orEmpty()
        )
    }
}