package hongsunghwi.portfolio.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import hongsunghwi_portfolio.composeapp.generated.resources.*
import org.jetbrains.compose.resources.Font

private val pretendardFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.Pretendard_Thin, FontWeight.Thin),
        Font(Res.font.Pretendard_ExtraLight, FontWeight.ExtraLight),
        Font(Res.font.Pretendard_Light, FontWeight.Light),
        Font(Res.font.Pretendard_Regular, FontWeight.Normal),
        Font(Res.font.Pretendard_Medium, FontWeight.Medium),
        Font(Res.font.Pretendard_SemiBold, FontWeight.SemiBold),
        Font(Res.font.Pretendard_Bold, FontWeight.Bold),
        Font(Res.font.Pretendard_ExtraBold, FontWeight.ExtraBold),
        Font(Res.font.Pretendard_Black, FontWeight.Black),
    )

val pretendardTypography: Typography
    @Composable
    get() {
        val ff = pretendardFontFamily
        return Typography(
            displayLarge = MaterialTheme.typography.displayLarge.copy(fontFamily = ff),
            displayMedium = MaterialTheme.typography.displayMedium.copy(fontFamily = ff),
            displaySmall = MaterialTheme.typography.displaySmall.copy(fontFamily = ff),
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(fontFamily = ff),
            headlineMedium = MaterialTheme.typography.headlineMedium.copy(fontFamily = ff),
            headlineSmall = MaterialTheme.typography.headlineSmall.copy(fontFamily = ff),
            titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = ff),
            titleMedium = MaterialTheme.typography.titleMedium.copy(fontFamily = ff),
            titleSmall = MaterialTheme.typography.titleSmall.copy(fontFamily = ff),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = ff),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = ff),
            bodySmall = MaterialTheme.typography.bodySmall.copy(fontFamily = ff),
            labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = ff),
            labelMedium = MaterialTheme.typography.labelMedium.copy(fontFamily = ff),
            labelSmall = MaterialTheme.typography.labelSmall.copy(fontFamily = ff)
        )
    }