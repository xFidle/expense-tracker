package pw.edu.pl.pap

import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun SystemBarsColors(statusBarColor: Color, navBarColor: Color) {
    val context = LocalContext.current as MainActivity
    context.enableEdgeToEdge(
        statusBarStyle = SystemBarStyle.dark(
            statusBarColor.toArgb()
        ), navigationBarStyle = SystemBarStyle.dark(navBarColor.toArgb())
    )
}