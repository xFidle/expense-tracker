package pw.edu.pl.pap.data.uiSetup.inputFields

import androidx.compose.ui.Modifier

data class ButtonData (
    val title: String,
    val modifier: Modifier = Modifier,
    val onClick: () -> Unit,
)