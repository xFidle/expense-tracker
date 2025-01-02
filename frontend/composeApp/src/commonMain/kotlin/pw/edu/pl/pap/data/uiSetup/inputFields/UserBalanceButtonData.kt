package pw.edu.pl.pap.data.uiSetup.inputFields

import androidx.compose.ui.graphics.Color

data class UserBalanceButtonData (
    val title: String,
    val balance: Float,
    val onClick: () -> Unit,
)