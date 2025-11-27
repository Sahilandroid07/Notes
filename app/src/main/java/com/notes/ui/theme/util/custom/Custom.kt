package com.notes.ui.theme.util.custom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.notes.ui.theme.util.custom_typo_color_shape.NotesAppTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MultiItemTicker(
    items: List<String>,
    modifier: Modifier = Modifier,
    durationPerItem: Int = 2000
) {
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(durationPerItem.toLong())
            currentIndex = (currentIndex + 1) % items.size
        }
    }

    AnimatedContent(
        targetState = currentIndex,
        transitionSpec = {
            (slideInVertically { height -> height } +
                    fadeIn()).togetherWith(slideOutVertically { height -> -height } +
                    fadeOut())
        }, label = ""
    ) { index ->
        Text(
            text = items[index],
            style = NotesAppTheme.typography.text_16_500,
            color = NotesAppTheme.colorScheme.black,
            modifier = modifier,
            maxLines = 1
        )
    }
}