package pw.edu.pl.pap.ui.chartsScreen.buttons

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import pw.edu.pl.pap.screenComponents.mainScreens.ChartsScreenComponent
import pw.edu.pl.pap.ui.common.RefreshButton
import pw.edu.pl.pap.util.charts.FilterTimeFrames
import pw.edu.pl.pap.util.dateFunctions.formatDate

@Composable
fun ButtonRow(
    component: ChartsScreenComponent,
) {
    val timeFrame by component.currentTimeFrame.collectAsState()
    val timeBounds by component.currentTimeBounds.collectAsState()
    
    val text = when (timeFrame) {
        FilterTimeFrames.MONTH -> formatDate(timeBounds.first!!, "MMMM yyyy")
        FilterTimeFrames.YEAR -> formatDate(timeBounds.first!!, "yyyy")
        else -> ""
    }
    
    Row {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            MonthYearSwitcher(
                text = text,
                onLeftArrowClick = { component.changeTimeBounds(LocalDate::minusWrapper) },
                onRightArrowClick = { component.changeTimeBounds(LocalDate::plusWrapper) },
            )
            Row(
                modifier = Modifier.align(Alignment.CenterEnd),
            ) {
                FilterMenuButton { }
                RefreshButton { }
            }
        }
    }
}

fun LocalDate.minusWrapper(value: Int, unit: DateTimeUnit): LocalDate {
    return this.minus(value, unit as DateTimeUnit.DateBased)
}

fun LocalDate.plusWrapper(value: Int, unit: DateTimeUnit): LocalDate {
    return this.plus(value, unit as DateTimeUnit.DateBased)
}