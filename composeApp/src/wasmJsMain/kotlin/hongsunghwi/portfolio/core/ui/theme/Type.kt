package hongsunghwi.portfolio.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import hongsunghwi_portfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

val pretendardFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.pretendard_thin, FontWeight.Thin),
        Font(Res.font.pretendard_extra_light, FontWeight.ExtraLight),
        Font(Res.font.pretendard_light, FontWeight.Light),
        Font(Res.font.pretendard_regular, FontWeight.Normal),
        Font(Res.font.pretendard_medium, FontWeight.Medium),
        Font(Res.font.pretendard_semi_bold, FontWeight.SemiBold),
        Font(Res.font.pretendard_bold, FontWeight.Bold),
        Font(Res.font.pretendard_extra_bold, FontWeight.ExtraBold),
        Font(Res.font.pretendard_black, FontWeight.Black),
    )

val pretendardTypography: Typography
    @Composable
    get() = Typography().run {
        copy(
            displayLarge = displayLarge.copy(fontFamily = pretendardFontFamily),
            displayMedium = displayMedium.copy(fontFamily = pretendardFontFamily),
            displaySmall = displaySmall.copy(fontFamily = pretendardFontFamily),
            headlineLarge = headlineLarge.copy(fontFamily = pretendardFontFamily),
            headlineMedium = headlineMedium.copy(fontFamily = pretendardFontFamily),
            headlineSmall = headlineSmall.copy(fontFamily = pretendardFontFamily),
            titleLarge = titleLarge.copy(fontFamily = pretendardFontFamily),
            titleMedium = titleMedium.copy(fontFamily = pretendardFontFamily),
            titleSmall = titleSmall.copy(fontFamily = pretendardFontFamily),
            bodyLarge = bodyLarge.copy(fontFamily = pretendardFontFamily),
            bodyMedium = bodyMedium.copy(fontFamily = pretendardFontFamily),
            bodySmall = bodySmall.copy(fontFamily = pretendardFontFamily),
            labelLarge = labelLarge.copy(fontFamily = pretendardFontFamily),
            labelMedium = labelMedium.copy(fontFamily = pretendardFontFamily),
            labelSmall = labelSmall.copy(fontFamily = pretendardFontFamily)
        )
    }