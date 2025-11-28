package com.notes.presentation_ui.add_update_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.notes.ui.theme.util.custom_typo_color_shape.NotesAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPickerBottomSheet(
    colors: List<Color>,
    sheetState: SheetState,
    onColorSelected: (Color) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        containerColor = NotesAppTheme.colorScheme.white,
        shape = NotesAppTheme.shapes.rounded_corner_24
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pick a background color",
                style = NotesAppTheme.typography.text_18_500,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                items(colors) { color ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(color = color)
                            .border(
                                width = 2.dp,
                                color = NotesAppTheme.colorScheme.black.copy(alpha = 0.2f),
                                shape = CircleShape
                            )
                            .clickable {
                                onColorSelected(color)
                                onDismiss()
                            }
                    )
                }
            }
        }
    }
}