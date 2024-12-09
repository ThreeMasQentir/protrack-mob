package org.gspi.protrack.gspidesign.font

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import gspiprotrack.composeapp.generated.resources.Res
import gspiprotrack.composeapp.generated.resources.poppins_black
import gspiprotrack.composeapp.generated.resources.poppins_blackitalic
import gspiprotrack.composeapp.generated.resources.poppins_bold
import gspiprotrack.composeapp.generated.resources.poppins_bolditalic
import gspiprotrack.composeapp.generated.resources.poppins_extrabold
import gspiprotrack.composeapp.generated.resources.poppins_extrabolditalic
import gspiprotrack.composeapp.generated.resources.poppins_extralight
import gspiprotrack.composeapp.generated.resources.poppins_extralightitalic
import gspiprotrack.composeapp.generated.resources.poppins_italic
import gspiprotrack.composeapp.generated.resources.poppins_light
import gspiprotrack.composeapp.generated.resources.poppins_lightitalic
import gspiprotrack.composeapp.generated.resources.poppins_medium
import gspiprotrack.composeapp.generated.resources.poppins_mediumitalic
import gspiprotrack.composeapp.generated.resources.poppins_regular
import gspiprotrack.composeapp.generated.resources.poppins_semibold
import gspiprotrack.composeapp.generated.resources.poppins_semibolditalic
import gspiprotrack.composeapp.generated.resources.poppins_thin
import gspiprotrack.composeapp.generated.resources.poppins_thinitalic
import org.jetbrains.compose.resources.Font

@Composable
fun PoppinsFontFamily() = FontFamily(
    Font(Res.font.poppins_thin, weight = FontWeight.Thin),
    Font(Res.font.poppins_thinitalic, weight = FontWeight.Thin, style = FontStyle.Italic),
    Font(Res.font.poppins_extralight, weight = FontWeight.ExtraLight),
    Font(Res.font.poppins_extralightitalic, weight = FontWeight.ExtraLight, style = FontStyle.Italic),
    Font(Res.font.poppins_light, weight = FontWeight.Light),
    Font(Res.font.poppins_lightitalic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(Res.font.poppins_regular, weight = FontWeight.Normal),
    Font(Res.font.poppins_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(Res.font.poppins_medium, weight = FontWeight.Medium),
    Font(Res.font.poppins_mediumitalic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(Res.font.poppins_semibold, weight = FontWeight.SemiBold),
    Font(Res.font.poppins_semibolditalic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(Res.font.poppins_bold, weight = FontWeight.Bold),
    Font(Res.font.poppins_bolditalic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(Res.font.poppins_extrabold, weight = FontWeight.ExtraBold),
    Font(Res.font.poppins_extrabolditalic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(Res.font.poppins_black, weight = FontWeight.Black),
    Font(Res.font.poppins_blackitalic, weight = FontWeight.Black, style = FontStyle.Italic),

)

@Composable
fun PoppinsTypography() = Typography().run {
    val fontFamily = PoppinsFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}