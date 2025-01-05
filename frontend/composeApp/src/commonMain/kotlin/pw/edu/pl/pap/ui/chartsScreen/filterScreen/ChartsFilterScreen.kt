package pw.edu.pl.pap.ui.chartsScreen.filterScreen

import androidx.compose.runtime.*
import pw.edu.pl.pap.screenComponents.chartsScreens.ChartsFilterScreenComponent
import pw.edu.pl.pap.ui.common.Header
import pw.edu.pl.pap.ui.common.InputFields

@Composable
fun ChartsFilterScreen(component: ChartsFilterScreenComponent) {
    Header("Filter data")

    InputFields(component.inputFieldsData)

    ChartsFilterScreenButtonRow(
        onBack = component.onDismiss, onConfirm = component::confirm
    )
}