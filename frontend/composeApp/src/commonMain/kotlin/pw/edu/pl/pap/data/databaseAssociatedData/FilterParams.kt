package pw.edu.pl.pap.data.databaseAssociatedData

import kotlinx.datetime.LocalDate

sealed class FilterParams() {
    abstract fun toMap(): Map<String, String>
}

data class ChartFilterParams(
    val beginDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val currCode: String? = null,
    val categoryNames: List<String>? = null,
    val emails: List<String>? = null,
    val methods: List<String>? = null
) : FilterParams() {
    override fun toMap(): Map<String, String> {
        return mapOf(
            "beginDate" to beginDate?.toString().orEmpty(),
            "endDate" to endDate?.toString().orEmpty(),
            "currCode" to currCode.orEmpty(),
            "categoryNames" to categoryNames?.toString().orEmpty(),
            "emails" to emails?.toString().orEmpty(),
            "methods" to methods?.toString().orEmpty()
        )
    }
}