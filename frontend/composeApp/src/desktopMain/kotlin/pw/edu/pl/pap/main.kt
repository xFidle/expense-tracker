package pw.edu.pl.pap

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Expense tracker",
    ) {
        App()
    }
}