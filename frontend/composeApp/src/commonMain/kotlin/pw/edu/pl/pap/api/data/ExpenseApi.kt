package pw.edu.pl.pap.api.data

import de.jensklingenberg.ktorfit.http.*
import io.ktor.client.statement.*
import pw.edu.pl.pap.data.databaseAssociatedData.*

interface ExpenseApi {

    @GET("expense/{id}")
    suspend fun getExpense(@Path("id") id: Long): Expense

    @GET("expense/recent/{group}")
    suspend fun getRecentExpense(@Path("group") group: String): Expense

    @GET("expense/total/{group}")
    suspend fun getTotalExpenses(@Path("group") group: String): TotalExpenses

    @GET("expense/dateMap/{group}")
    suspend fun getExpenseDateMap(
        @Path("group") group: String,
        @QueryMap paramsMap: Map<String, String>
    ): DateKeyExpensePage

    @GET("expense/categoryMap/{group}")
    suspend fun getExpenseCatMap(
        @Path("group") group: String,
        @QueryMap paramsMap: Map<String, String>
    ): StringKeyExpensePage

    @POST("expense")
    suspend fun postNewExpense(@Body newExpense: NewExpense): HttpResponse

    @PUT("expense/{id}")
    suspend fun updateExpense(@Path("id") id: Long, @Body expense: Expense): HttpResponse

    @DELETE("expense/{id}")
    suspend fun deleteExpense(@Path("id") id: Long): HttpResponse
}