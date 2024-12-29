package pw.edu.pl.pap.ui.settingsScreen

import androidx.compose.runtime.Composable
import pw.edu.pl.pap.screenComponents.mainScreens.SettingsScreenComponent
import pw.edu.pl.pap.ui.common.Header
import pw.edu.pl.pap.ui.common.InputFields

@Composable
fun SettingsScreen(component: SettingsScreenComponent) {

    Header("Settings")

    component.setupInputFields()
    InputFields(component.inputFieldsData)
}