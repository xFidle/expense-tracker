package pw.edu.pl.pap.api

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.LocalDate
import pw.edu.pl.pap.data.Expense
import pw.edu.pl.pap.data.NewExpense
import pw.edu.pl.pap.data.TotalExpenses

class ExpenseApiClient(baseUrl: String, httpClient: HttpClient) : BaseApiClient(baseUrl, httpClient) {

    suspend fun getTotalExpenses(userGroup: String, userEmail: String): TotalExpenses {
        return get("state/group/$userGroup/user/$userEmail").body()
    }

    private suspend fun getAllExpensesApi(): Map<LocalDate, List<Expense>> {
        return get("all/dateMap").body()
    }

    fun getAllExpenses() = flow {
        emit(getAllExpensesApi())
    }

    private suspend fun getExpenseApi(id: Long): Expense {
        return get("$id").body()
    }

    fun getExpense(id: Long) = flow {
        emit(getExpenseApi(id))
    }

    private suspend fun getRecentExpenseApi(): Expense {
        return get("recent").body()
    }

    fun getRecentExpense() = flow {
        emit(getRecentExpenseApi())
    }

    suspend fun updateExpense(expense: Expense): HttpResponse {
        return put("update/${expense.id}", expense)
    }

    suspend fun postNewExpense(newExpense: NewExpense) {
        println("expense to be uploaded  $newExpense")

        val response: HttpResponse = post("insert", newExpense)

        println("Response  " + response.body())
    }
}