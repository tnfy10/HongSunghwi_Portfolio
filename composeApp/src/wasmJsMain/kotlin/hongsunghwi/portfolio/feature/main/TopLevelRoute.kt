package hongsunghwi.portfolio.feature.main

import ExperiencesRoute
import hongsunghwi.portfolio.feature.about.AboutRoute
import hongsunghwi.portfolio.feature.education.EducationRoute
import hongsunghwi.portfolio.feature.projects.ProjectsRoute
import hongsunghwi.portfolio.feature.skills.SkillsRoute
import hongsunghwi_portfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class TopLevelRoute<T: Any>(
    val label: String,
    val route: T,
    val icon: DrawableResource
)

val topLevelRoutes = listOf(
    TopLevelRoute(
        label = "About me",
        route = AboutRoute,
        icon = Res.drawable.ic_face_24
    ),
    TopLevelRoute(
        label = "Experiences",
        route = ExperiencesRoute,
        icon = Res.drawable.ic_stacks_24
    ),
    TopLevelRoute(
        label = "Projects",
        route = ProjectsRoute,
        icon = Res.drawable.ic_view_apps_24
    ),
    TopLevelRoute(
        label = "Skills",
        route = SkillsRoute,
        icon = Res.drawable.ic_code_24
    ),
    TopLevelRoute(
        label = "Education",
        route = EducationRoute,
        icon = Res.drawable.ic_school_24
    )
)