package pw.edu.pl.pap.ui.chartsScreen.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MonthYearSwitcher(
    text: String,
    onLeftArrowClick: () -> Unit,
    onRightArrowClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onLeftArrowClick) {
            Icon(Icons.Filled.ChevronLeft, contentDescription = "Previous time frame")
        }
        Box(
            modifier = Modifier.width(200.dp), // Set a fixed width for the text box
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        IconButton(onClick = onRightArrowClick) {
            Icon(Icons.Filled.ChevronRight, contentDescription = "Next time frame")
        }
    }
}