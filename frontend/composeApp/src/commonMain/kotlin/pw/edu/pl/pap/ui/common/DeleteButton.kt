package pw.edu.pl.pap.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DeleteButton(modifier: Modifier = Modifier, onUpdate: () -> Unit = {}) {
    Button(
        onClick = onUpdate,
        shape = RoundedCornerShape(20),
        modifier = modifier.width(100.dp).height(60.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error, contentColor = MaterialTheme.colorScheme.onError
        ),
        contentPadding = PaddingValues(0.dp),

        ) {
        Text(
            text = "DELETE",
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}