package hongsunghwi.portfolio.feature.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.constant.Container
import hongsunghwi.portfolio.core.constant.Container.*
import hongsunghwi.portfolio.core.constant.Size
import hongsunghwi.portfolio.feature.about.AboutContainer
import hongsunghwi.portfolio.feature.career.CareerContainer
import hongsunghwi.portfolio.feature.project.ProjectsContainer
import hongsunghwi.portfolio.feature.skill.SkillsContainer
import hongsunghwi_portfolio.composeapp.generated.resources.*
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var screenWidth by remember { mutableStateOf(Dp.Unspecified) }
    val isSmallScreen by remember(screenWidth) {
        mutableStateOf(screenWidth < Size.BASE_SCREEN_WIDTH)
    }
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    LaunchedEffect(isSmallScreen) {
        if (!isSmallScreen) {
            drawerState.close()
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.surface
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
                                style = MaterialTheme.typography.labelLarge
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
        val uriHandler = LocalUriHandler.current

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
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    uriHandler.openUri("https://github.com/tnfy10")
                                }
                            ) {
                                Icon(
                                    painter = if (isSystemInDarkTheme()) {
                                        painterResource(Res.drawable.ic_github_dark)
                                    } else {
                                        painterResource(Res.drawable.ic_github)
                                    },
                                    contentDescription = "GitHub"
                                )
                            }
                        }
                    )
                }
            },
            floatingActionButton = {
                AnimatedVisibility(
                    visible = listState.firstVisibleItemIndex != 0,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    FloatingActionButton(
                        onClick = {
                            scope.launch {
                                listState.animateScrollToItem(0)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_arrow_upward_24),
                            contentDescription = "위로가기"
                        )
                    }
                }
            }
        ) { innerPadding ->
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                if (!isSmallScreen) {
                    NavigationRail(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                        header = {
                            FloatingActionButton(
                                onClick = {
                                    uriHandler.openUri("https://github.com/tnfy10")
                                },
                                modifier = Modifier.padding(vertical = 16.dp)
                            ) {
                                Icon(
                                    painter = if (isSystemInDarkTheme()) {
                                        painterResource(Res.drawable.ic_github_dark)
                                    } else {
                                        painterResource(Res.drawable.ic_github)
                                    },
                                    contentDescription = "GitHub"
                                )
                            }
                        }
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
                                            style = MaterialTheme.typography.labelMedium
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
                        modifier = Modifier.widthIn(max = Size.BASE_SCREEN_WIDTH),
                        state = listState,
                        contentPadding = PaddingValues(
                            top = if (isSmallScreen) 48.dp else 96.dp
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

                                SKILLS -> {
                                    SkillsContainer(
                                        modifier = Modifier.fillMaxWidth(),
                                        isSmallScreen = isSmallScreen
                                    )
                                }

                                CAREER -> {
                                    CareerContainer(
                                        modifier = Modifier.fillMaxWidth(),
                                        isSmallScreen = isSmallScreen
                                    )
                                }

                                PROJECTS -> {
                                    ProjectsContainer(
                                        modifier = Modifier.fillMaxWidth(),
                                        isSmallScreen = isSmallScreen,
                                        columns = if (screenWidth <= Size.BASE_MOBILE_SCREEN_WIDTH) 1 else 2
                                    )
                                }
                            }
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Compose Multiplatform으로 제작된 사이트입니다.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        textAlign = TextAlign.Center
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}