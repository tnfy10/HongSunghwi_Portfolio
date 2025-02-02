package hongsunghwi.portfolio.core.constant

import hongsunghwi_portfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource

enum class Container(
    val label: String,
    val icon: DrawableResource
) {
    ABOUT_ME(
        label = "About me",
        icon = Res.drawable.ic_face_24
    ),
    CAREER(
        label = "Career",
        icon = Res.drawable.ic_stacks_24
    ),
    PROJECTS(
        label = "Projects",
        icon = Res.drawable.ic_view_apps_24
    ),
    SKILLS(
        label = "Skills",
        icon = Res.drawable.ic_code_24
    ),
    EDUCATION(
        label = "Education",
        icon = Res.drawable.ic_school_24
    )
}