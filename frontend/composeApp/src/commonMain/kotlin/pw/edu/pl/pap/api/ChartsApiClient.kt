package pw.edu.pl.pap.api

import io.ktor.client.call.*
import pw.edu.pl.pap.api.endpoints.ChartEndpoint
import pw.edu.pl.pap.data.databaseAssociatedData.ChartFilterParams
import pw.edu.pl.pap.util.charts.ChartData

class ChartsApiClient(baseApiClient: BaseApiClient) :
    ApiClient by baseApiClient {

    suspend fun getData(group: String, keyPattern: String, filterParams: ChartFilterParams): ChartData {
        val map: Map<String, Float> = get(ChartEndpoint.FilteredData(group, keyPattern, filterParams)).body()
        return map.toMap(ChartData())
    }
}