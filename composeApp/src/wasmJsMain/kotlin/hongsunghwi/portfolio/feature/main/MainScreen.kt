package hongsunghwi.portfolio.feature.main

import ExperiencesRoute
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.onClick
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hongsunghwi.portfolio.core.util.isMobile
import hongsunghwi.portfolio.feature.about.AboutRoute
import hongsunghwi.portfolio.feature.about.AboutScreen
import hongsunghwi.portfolio.feature.education.EducationRoute
import hongsunghwi.portfolio.feature.education.EducationScreen
import hongsunghwi.portfolio.feature.experiences.ExperiencesScreen
import hongsunghwi.portfolio.feature.projects.ProjectsRoute
import hongsunghwi.portfolio.feature.projects.ProjectsScreen
import hongsunghwi.portfolio.feature.skills.SkillsRoute
import hongsunghwi.portfolio.feature.skills.SkillsScreen
import hongsunghwi_portfolio.composeapp.generated.resources.Res
import hongsunghwi_portfolio.composeapp.generated.resources.ic_menu_24
import hongsunghwi_portfolio.composeapp.generated.resources.ic_menu_open_24
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

private const val BASE_SCREEN_WIDTH = 1360

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var screenWidth by remember { mutableStateOf(0) }

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
                topLevelRoutes.forEach { topLevelRoute ->
                    ListItem(
                        headlineContent = {
                            Text(
                                text = topLevelRoute.label
                            )
                        },
                        modifier = Modifier.clickable {
                            navController.navigate(topLevelRoute.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true
                            }
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        leadingContent = {
                            Icon(
                                painter = painterResource(topLevelRoute.icon),
                                contentDescription = topLevelRoute.label
                            )
                        }
                    )
                }
            }
        },
        drawerState = drawerState,
        gesturesEnabled = false
    ) {
        Scaffold(
            modifier = Modifier.onClick {
                if (drawerState.isOpen) {
                    scope.launch {
                        drawerState.close()
                    }
                }
            }.onSizeChanged {
                screenWidth = it.width
            },
            topBar = {
                if (!shouldShowNavigationRail(screenWidth)) {
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
                if (shouldShowNavigationRail(screenWidth)) {
                    NavigationRail {
                        topLevelRoutes.forEach { topLevelRoute ->
                            NavigationRailItem(
                                selected = currentDestination?.hierarchy?.any {
                                    it.hasRoute(
                                        topLevelRoute.route::class
                                    )
                                } == true,
                                onClick = {
                                    navController.navigate(topLevelRoute.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        restoreState = true
                                        launchSingleTop = true
                                    }
                                },
                                icon = {
                                    Icon(
                                        painter = painterResource(topLevelRoute.icon),
                                        contentDescription = topLevelRoute.label
                                    )
                                },
                                label = {
                                    Text(
                                        text = topLevelRoute.label
                                    )
                                }
                            )
                        }
                    }
                }
                NavHost(
                    navController = navController,
                    startDestination = AboutRoute,
                    modifier = Modifier.weight(1f)
                ) {
                    composable<AboutRoute> {
                        AboutScreen(
                            innerPadding = innerPadding,
                            onNavigateToExperiences = {
                                navController.navigate(ExperiencesRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                    composable<ExperiencesRoute> {
                        ExperiencesScreen(
                            innerPadding = innerPadding,
                            onNavigateToAbout = {
                                navController.navigate(AboutRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                            onNavigateToProjects = {
                                navController.navigate(ProjectsRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                    composable<ProjectsRoute> {
                        ProjectsScreen(
                            innerPadding = innerPadding,
                            onNavigateToSkills = {
                                navController.navigate(SkillsRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                            onNavigateToExperience = {
                                navController.navigate(ExperiencesRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                    composable<SkillsRoute> {
                        SkillsScreen(
                            innerPadding = innerPadding,
                            onNavigateToProjects = {
                                navController.navigate(ProjectsRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            },
                            onNavigateToEducation = {
                                navController.navigate(EducationRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                    composable<EducationRoute> {
                        EducationScreen(
                            innerPadding = innerPadding,
                            onNavigateToSkills = {
                                navController.navigate(SkillsRoute) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    LaunchedEffect(shouldShowNavigationRail(screenWidth)) {
        if (shouldShowNavigationRail(screenWidth)) {
            drawerState.close()
        }
    }
}

private fun shouldShowNavigationRail(screenWidth: Int): Boolean {
    return !isMobile() && screenWidth >= BASE_SCREEN_WIDTH
}