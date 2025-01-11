package pw.edu.pl.pap.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import pw.edu.pl.pap.screenComponents.mainScreens.HomeScreenComponent
import pw.edu.pl.pap.ui.common.PaginatedLazyColumn
import pw.edu.pl.pap.ui.common.UserGroupPopup
import pw.edu.pl.pap.ui.home.sortingSystem.ButtonRow
import pw.edu.pl.pap.ui.home.sortingSystem.GroupKeyPopup

private const val buffer = 5

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(component: HomeScreenComponent) {
//    val loadingData by component.loadingData.collectAsState()
    var showGroupingKeyMenu by remember { mutableStateOf(false) }
    var showUserGroupMenu by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
//    val groupedExpenses by component.groupedExpenses.collectAsState()


    LaunchedEffect(component.navigationState.collectAsState().value) {
        component.getDataBasedOnState()
    }

//    val reachedBottom by remember { derivedStateOf { listState.reachedBottom(5) && component.moreToLoad.value && !loadingData } }
//
//    LaunchedEffect(reachedBottom) {
//        println(listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index)
//        if (reachedBottom) component.fetchNextPage()
//    }

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
                TopSection(component)

                ButtonRow(
                    component = component,
                    onGroupKeyClick = { showGroupingKeyMenu = true },
                    onUserGroupClick = { showUserGroupMenu = true })
            }
        },
        floatingActionButton = {
            PlusButton(onUpdate = {
                component.currentUserGroup.value?.let { userGroup ->
                    component.onAddExpenseButtonClicked(userGroup)
                } ?: run {
                    println("No UserGroup is set!")
                }
            })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            PaginatedLazyColumn(
                component = component,
                listState = listState,
                buffer = buffer,
            )
        }
    }

    AnimatedVisibility(
        visible = showGroupingKeyMenu, enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight }), exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight })
    ) {
        GroupKeyPopup(
            component = component,
            onDismiss = { showGroupingKeyMenu = false },
        )
    }


    AnimatedVisibility(
        visible = showUserGroupMenu, enter = slideInVertically(
            initialOffsetY = { fullHeight -> fullHeight }), exit = slideOutVertically(
            targetOffsetY = { fullHeight -> fullHeight })
    ) {
        UserGroupPopup(
            component = component, onDismiss = { showUserGroupMenu = false })
    }
}