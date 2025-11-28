package com.notes.ui.theme.util.custom_typo_color_shape

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

class NotesShapes(
    val top_rounded_corner_20: CornerBasedShape = Shapes.top_rounded_corner_20,
    val bottom_rounded_corner_20: CornerBasedShape = Shapes.bottom_rounded_corner_20,
    val rounded_corner_20: CornerBasedShape = Shapes.rounded_corner_20,
    val top_rounded_corner_24: CornerBasedShape = Shapes.top_rounded_corner_24,
    val bottom_rounded_corner_24: CornerBasedShape = Shapes.bottom_rounded_corner_24,
    val rounded_corner_24: CornerBasedShape = Shapes.rounded_corner_24,
    val top_rounded_corner_32: CornerBasedShape = Shapes.top_rounded_corner_32,
    val bottom_rounded_corner_32: CornerBasedShape = Shapes.bottom_rounded_corner_32,
    val rounded_corner_32: CornerBasedShape = Shapes.rounded_corner_32,

)

private object Shapes {
    val top_rounded_corner_20: CornerBasedShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    val bottom_rounded_corner_20: CornerBasedShape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
    val rounded_corner_20: CornerBasedShape = RoundedCornerShape(20.dp)
    val top_rounded_corner_24: CornerBasedShape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
    val bottom_rounded_corner_24: CornerBasedShape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
    val rounded_corner_24: CornerBasedShape = RoundedCornerShape(24.dp)
    val top_rounded_corner_32: CornerBasedShape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
    val bottom_rounded_corner_32: CornerBasedShape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
    val rounded_corner_32: CornerBasedShape = RoundedCornerShape(32.dp)
}


