package pw.edu.pl.pap.screenComponents.singleExpense

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import pw.edu.pl.pap.data.databaseAssociatedData.NewExpense
import pw.edu.pl.pap.data.databaseAssociatedData.User
import pw.edu.pl.pap.data.databaseAssociatedData.UserGroup
import pw.edu.pl.pap.screenComponents.mainScreens.BaseScreenComponent

class NewExpenseScreenComponent(
    baseComponent: BaseScreenComponent,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    private val currentUserGroup: UserGroup,
) : BaseExpenseScreenComponent(baseComponent, onDismiss, onSave) {


    override fun confirm() {
        val newExpense = NewExpense(
            title = title.value,
            price = newPrice.value.toFloat(),
            user = users[userIndex.value],
            groupName = currentUserGroup.name,
            categoryName = categories[categoryIndex.value],
            expenseDate = date.value,
            methodOfPayment = methodsOfPayment[methodOfPaymentIndex.value],
            currencyCode = currencies[currencyIndex.value]
        )

        runBlocking{
            apiService.expenseApiClient.postNewExpense(newExpense)
            onSave()
        }

        println("confirmed " + newExpense.price)
    }

}