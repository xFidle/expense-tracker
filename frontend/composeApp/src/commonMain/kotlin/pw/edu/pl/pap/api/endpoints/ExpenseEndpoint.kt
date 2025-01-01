package pw.edu.pl.pap.api.endpoints

sealed class ExpenseEndpoint(relativePath: String) : BaseEndpoint("/expense", relativePath) {
    data class RecentExpense(val group: String) : ExpenseEndpoint("/recent/$group")
    data object NewExpense : ExpenseEndpoint("/create")
    data class DeleteExpense(val id: Number) : ExpenseEndpoint("/delete/$id")
    data class ExpenseById(val id: Number) : ExpenseEndpoint("/$id")
    data class UpdateExpense(val id: Number) : ExpenseEndpoint("/update/$id")
    data class TotalGroupExpense(val group: String) : ExpenseEndpoint("/state/$group")
    data class GroupDateMap(val group: String) : ExpenseEndpoint("/dateMap/group/$group")
    data class GroupCatMap(val group: String) : ExpenseEndpoint("/categoryMap/group/$group")
}