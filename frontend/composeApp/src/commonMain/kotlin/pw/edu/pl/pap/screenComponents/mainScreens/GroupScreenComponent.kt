package pw.edu.pl.pap.screenComponents.mainScreens

import pw.edu.pl.pap.data.databaseAssociatedData.User
import pw.edu.pl.pap.data.databaseAssociatedData.UserGroup

class GroupScreenComponent(
    private val onUserClicked: (User) -> Unit,
    private val currentUserGroup: UserGroup,
    baseComponent: BaseScreenComponent,
) : BaseScreenComponent by baseComponent {

}