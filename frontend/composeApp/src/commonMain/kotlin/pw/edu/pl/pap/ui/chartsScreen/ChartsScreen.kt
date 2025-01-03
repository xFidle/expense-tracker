package pw.edu.pl.pap.ui.chartsScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.edu.pl.pap.screenComponents.mainScreens.ChartsScreenComponent
import pw.edu.pl.pap.util.constants.horizontalPadding


@Composable
fun ChartsScreen(component: ChartsScreenComponent) {
    var selectedTab by remember { mutableStateOf("Month") }
    val tabs = listOf("Month", "Year", "Custom")

    var isGroupMenuExpanded by remember { mutableStateOf(false) }
    val selectedGroup by component.currentUserGroup.collectAsState()
    val groups by component.userGroupInfo.collectAsState()

    LaunchedEffect(component.navigationState.collectAsState()) {
        component.getDataBasedOnState()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = tabs.indexOf(selectedTab), modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(selected = selectedTab == tab, onClick = { selectedTab = tab }, text = { Text(tab) })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth().height(200.dp).padding(8.dp), contentAlignment = Alignment.Center
        ) {
            Text(text = "Pie Chart Placeholder")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = horizontalPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box {
                TextButton(onClick = { isGroupMenuExpanded = true }) {
                    selectedGroup?.let { Text(text = it.name) }
                }
                DropdownMenu(
                    expanded = isGroupMenuExpanded, onDismissRequest = { isGroupMenuExpanded = false }) {
                    groups.forEachIndexed { idx, group ->
                        DropdownMenuItem(text = { Text(group.name) }, onClick = {
                            component.updateUserGroup(groups[idx])
                            isGroupMenuExpanded = false
                        })
                    }
                }
            }
            Text(
                text = "Total: ${component.getTotal()} zł",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(20) { index ->
                ListItem(
                    headlineContent = { Text("Item $index") },
                    supportingContent = { Text("Details for Item $index") })
                HorizontalDivider()
            }
        }
    }
}
