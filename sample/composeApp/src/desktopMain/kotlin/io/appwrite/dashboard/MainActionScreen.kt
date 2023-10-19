package io.appwrite.dashboard

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.appwrite.models.MainActionDto
import io.appwrite.navigation.NavController

@Composable
fun mainActionScreen(
    mainAction: MainActionDto,
    index: Int,
    click: (Int) -> Unit,
    navController: NavController,
    showText: Boolean
) {
    val textAlphaDuration = if (showText) 200 else 0
    val alpha by animateFloatAsState(
        if (showText) 1f else 0f,
        tween(durationMillis = textAlphaDuration, easing = LinearEasing)
    )

    Box(modifier = Modifier.padding(bottom = 2.dp).background(Color(0XFF141415))) {
        val active = mainAction.index == index
        val textColor = if (active) {
            Color.White
        }else Color.LightGray

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .clickable {
                    click(mainAction.index)
                    navController.navigate(mainAction.screen.name)
                }) {
            if (active) {
                Spacer(
                    modifier = Modifier.width(2.dp).fillMaxHeight().padding(vertical = 2.dp)
                        .background(MaterialTheme.colors.primary)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(mainAction.title, fontSize = 14.sp, color = textColor, modifier = Modifier.alpha(alpha))
        }
    }
}