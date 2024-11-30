package pw.edu.pl.pap.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pw.edu.pl.pap.data.Home
import pw.edu.pl.pap.viewmodel.HomeViewModel
import pw.edu.pl.pap.ui.common.LoadingScreen


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var isLoading by remember { mutableStateOf(true) }
    val homeInfo = viewModel.homeInfo.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchHomeInfo()
        isLoading = false
    }

    if (isLoading) {
        LoadingScreen()
    } else if (homeInfo != null) {
        TopSection(homeInfo)
    } else {
        return
    }
}

@Composable
fun TopSection(homeInfo: Home) {
    Row(Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "My Expenses",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${homeInfo.userExpenses} zł",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Total Expenses",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${homeInfo.expenses} zł",
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}