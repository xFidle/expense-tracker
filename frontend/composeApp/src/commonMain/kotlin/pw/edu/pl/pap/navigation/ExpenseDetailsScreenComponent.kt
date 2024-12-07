package pw.edu.pl.pap.navigation

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import pw.edu.pl.pap.apiclient.ApiClient
import pw.edu.pl.pap.data.Record

class ExpenseDetailsScreenComponent (
    componentContext: ComponentContext,
    private val apiClient: ApiClient,
    private val coroutineScope: CoroutineScope,
    val record: Record,
    val onBack: () -> Unit
) {

}