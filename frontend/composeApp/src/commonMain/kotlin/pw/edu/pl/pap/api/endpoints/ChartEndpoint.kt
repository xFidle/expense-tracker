package pw.edu.pl.pap.api.endpoints

import pw.edu.pl.pap.data.databaseAssociatedData.ChartFilterParams
import pw.edu.pl.pap.util.api.generateParamRequest

sealed class ChartEndpoint(relativePath: String) : BaseEndpoint("/chart/map-result", relativePath) {
    data class FilteredData(val group: String, val keyPattern: String, val filterParams: ChartFilterParams) :
        ChartEndpoint("/$group/$keyPattern${generateParamRequest(filterParams)}")
}