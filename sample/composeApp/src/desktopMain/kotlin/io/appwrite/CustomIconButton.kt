package io.appwrite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomIconButton(icon: String, tooltipText: String = "", enable: Boolean = true, color: Color = Color.Gray, onClick: () -> Unit) {
    val enableState = mutableStateOf(enable)

    val disableColor = if (enable) color else Color.DarkGray

    CompositionLocalProvider(LocalRippleTheme provides RippleCustomTheme) {

        TooltipArea(
            tooltip = {
                if (tooltipText.isNotBlank()){
                    Surface(
                        modifier = Modifier.shadow(4.dp),
                        color = MaterialTheme.colors.primaryVariant,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = tooltipText,
                            modifier = Modifier.padding(4.dp),
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            },
            tooltipPlacement = TooltipPlacement.CursorPoint(),
            delayMillis = 300,
            content = {
                IconButton(
                    enabled = enableState.value,
                    onClick = {
                        onClick()
                    }, modifier = Modifier.padding(horizontal = 5.dp).size(15.dp)
                ) {
                    Icon(
                        painter = painterResource(icon),
                        "",
                        tint = disableColor
                    )
                }
            }
        )
    }
}

object RippleCustomTheme: RippleTheme {

    @Composable
    override fun defaultColor() =
        RippleTheme.defaultRippleColor(
            Color.Transparent,
            lightTheme = true
        )

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleTheme.defaultRippleAlpha(
            Color.Transparent,
            lightTheme = true
        )
}