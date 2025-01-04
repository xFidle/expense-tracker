package pw.edu.pl.pap.util.dateFunctions

import kotlinx.datetime.LocalDate

fun dateToMilis(date: LocalDate): Long {
    val days = date.toEpochDays().toLong()
    return days * 24 * 60 * 60 * 1000
}