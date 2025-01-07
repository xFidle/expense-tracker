package pw.edu.pl.pap.util.api

import pw.edu.pl.pap.data.databaseAssociatedData.FilterParams

fun generateParamRequest(filterParams: FilterParams): String {
    var query = "?"
    val propertyMap = filterParams.toMap()
    propertyMap.forEach { (key, value) ->
        if (value == "") return@forEach
        val param = "$key=$value&".replace(Regex("[\\[\\] ]"), "")
        query += param
    }
    return query.removeSuffix("?").removeSuffix("&")
}