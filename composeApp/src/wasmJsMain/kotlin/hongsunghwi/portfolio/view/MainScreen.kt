package hongsunghwi.portfolio.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.onClick
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.Constant
import hongsunghwi.portfolio.view.Container.*
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import hongsunghwi_portfolio.composeapp.generated.resources.ic_menu_24
import hongsunghwi_portfolio.composeapp.generated.resources.ic_menu_open_24
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var screenWidth by remember { mutableStateOf(Dp.Unspecified) }
    val isSmallScreen by remember(screenWidth) {
        mutableStateOf(screenWidth >= Constant.BASE_SCREEN_WIDTH)
    }
    val listState = rememberLazyListState()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                TopAppBar(
                    title = {
                        Text(
                            text = "Menu"
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_menu_open_24),
                                contentDescription = null
                            )
                        }
                    }
                )
                Container.entries.forEachIndexed { index, container ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = container.label
                            )
                        },
                        modifier = Modifier.clickable {
                            scope.launch {
                                launch {
                                    listState.animateScrollToItem(index)
                                }
                                launch {
                                    drawerState.close()
                                }
                            }
                        },
                        leadingContent = {
                            Icon(
                                painter = painterResource(container.icon),
                                contentDescription = container.label
                            )
                        }
                    )
                }
            }
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ) {
        val density = LocalDensity.current

        Scaffold(
            modifier = Modifier.onClick {
                if (drawerState.isOpen) {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }.onSizeChanged {
                screenWidth = with(density) { it.width.toDp() }
            },
            topBar = {
                if (!isSmallScreen) {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Sunghwi's Portfolio",
                            )
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.ic_menu_24),
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                if (isSmallScreen) {
                    NavigationRail {
                        Container.entries.forEachIndexed { index, container ->
                            NavigationRailItem(
                                selected = listState.firstVisibleItemIndex == index,
                                onClick = {
                                    if (listState.firstVisibleItemIndex != index) {
                                        scope.launch {
                                            listState.animateScrollToItem(index)
                                        }
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(container.icon),
                                        contentDescription = container.label
                                    )
                                },
                                label = {
                                    Text(
                                        text = container.label
                                    )
                                }
                            )
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(horizontal = 16.dp)
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(
                        modifier = Modifier.widthIn(max = Constant.BASE_SCREEN_WIDTH),
                        state = listState
                    ) {
                        items(Container.entries) { item ->
                            when (item) {
                                ABOUT_ME -> {
                                    AboutContainer(
                                        modifier = Modifier.padding(
                                            vertical = if (isSmallScreen) 150.dp else 20.dp
                                        ),
                                        isSmallScreen = isSmallScreen
                                    )
                                }

                                CAREER -> {
                                    CareerContainer()
                                }

                                PROJECTS -> {
                                    ProjectsContainer()
                                }

                                SKILLS -> {
                                    SkillsContainer()
                                }

                                EDUCATION -> {
                                    EducationContainer()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(isSmallScreen) {
        if (isSmallScreen) {
            drawerState.close()
        }
    }
}