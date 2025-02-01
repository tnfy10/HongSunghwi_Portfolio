package hongsunghwi.portfolio.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.Constant
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme
import hongsunghwi.portfolio.view.Container.*
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import hongsunghwi_portfolio.composeapp.generated.resources.ic_menu_24
import hongsunghwi_portfolio.composeapp.generated.resources.ic_menu_open_24
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var screenWidth by remember { mutableStateOf(Dp.Unspecified) }
    val isSmallScreen by remember(screenWidth) {
        mutableStateOf(screenWidth < Constant.BASE_SCREEN_WIDTH)
    }
    val listState = rememberLazyListState()

    LaunchedEffect(isSmallScreen) {
        if (!isSmallScreen) {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = PortfolioTheme.colors.surface
            ) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .size(48.dp)
                        .clickable(
                            indication = ripple(radius = (48 / 2).dp),
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_menu_open_24),
                        contentDescription = "메뉴 닫기"
                    )
                }
                Container.entries.forEachIndexed { index, container ->
                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = container.label,
                                style = PortfolioTheme.typography.labelLarge
                            )
                        },
                        selected = index == listState.firstVisibleItemIndex,
                        onClick = {
                            scope.launch {
                                launch {
                                    listState.animateScrollToItem(index)
                                }
                                launch {
                                    drawerState.close()
                                }
                            }
                        },
                        modifier = Modifier.padding(horizontal = 8.dp),
                        icon = {
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
        gesturesEnabled = isSmallScreen
    ) {
        val density = LocalDensity.current

        Scaffold(
            modifier = Modifier.onSizeChanged {
                screenWidth = with(density) { it.width.toDp() }
            },
            topBar = {
                if (isSmallScreen) {
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
                                    contentDescription = "메뉴"
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
                if (!isSmallScreen) {
                    NavigationRail(
                        containerColor = PortfolioTheme.colors.surfaceContainer
                    ) {
                        Column(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
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
                                            text = container.label,
                                            style = PortfolioTheme.typography.labelMedium
                                        )
                                    }
                                )
                            }
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
                        state = listState,
                        contentPadding = PaddingValues(
                            top = if (isSmallScreen) 50.dp else 200.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(if (isSmallScreen) 100.dp else 300.dp)
                    ) {
                        items(Container.entries) { item ->
                            when (item) {
                                ABOUT_ME -> {
                                    AboutContainer(
                                        modifier = Modifier.fillMaxWidth(),
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
}