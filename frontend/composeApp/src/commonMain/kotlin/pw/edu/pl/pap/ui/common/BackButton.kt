package pw.edu.pl.pap.ui.common


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BackButton(modifier: Modifier = Modifier, onUpdate: () -> Unit = {}) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            shape = RoundedCornerShape(20),
            modifier = modifier
                .width(100.dp)
                .height(60.dp),
            contentPadding = PaddingValues(0.dp),
            onClick = { onUpdate() }
        ) {
            Text(
                text = "BACK",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}