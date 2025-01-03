package pw.edu.pl.pap.screenComponents.editGroupScreens

import pw.edu.pl.pap.data.databaseAssociatedData.Expense
import pw.edu.pl.pap.data.databaseAssociatedData.UserGroup
import pw.edu.pl.pap.screenComponents.mainScreens.BaseScreenComponent
import pw.edu.pl.pap.screenComponents.singleExpense.BaseExpenseScreenComponent

class EditGroupScreenComponent(
    baseComponent: BaseScreenComponent,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    private val onDelete: () -> Unit,
    private val group: UserGroup
) : BaseGroupEditScreenComponent(baseComponent, onDismiss, onSave) {

}