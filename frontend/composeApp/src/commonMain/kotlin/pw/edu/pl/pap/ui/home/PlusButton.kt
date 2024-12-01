package pw.edu.pl.pap.ui.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import 	androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PlusButton(): Unit {
    Button(
        shape = RoundedCornerShape(100),
        modifier = Modifier
            .width(50.dp)
            .height(50.dp),
        onClick = {}
    ) {
        Text("+")
    }
}
