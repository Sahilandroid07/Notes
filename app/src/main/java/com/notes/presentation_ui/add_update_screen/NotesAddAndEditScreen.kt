package com.notes.presentation_ui.add_update_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notes.R
import com.notes.ui.theme.NotesTheme
import com.notes.ui.theme.util.custom_modifier.CustomEditText
import com.notes.ui.theme.util.custom_modifier.RoundedCustomButton
import com.notes.ui.theme.util.custom_typo_color_shape.NotesAppTheme

@Composable
fun NotesAddAndEditScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesAddAndEditViewModel,
    onBackCLick: () -> Unit
) {
    val notesAddUiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NotesAppTheme.colorScheme.dark_grey)
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        TestAddNotesEditComponent(
            notesAddUiState = notesAddUiState,
            onAction = viewModel::onAction,
            onBackCLick = onBackCLick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestAddNotesEditComponent(
    modifier: Modifier = Modifier,
    notesAddUiState: NotesAddUiState?,
    onAction: (NoteAddAndEditIntent) -> Unit,
    onBackCLick: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var showColorPicker by remember { mutableStateOf(false) }

    val selectedColor = listOf(
        NotesAppTheme.colorScheme.orange,
        NotesAppTheme.colorScheme.white,
        NotesAppTheme.colorScheme.purple,
        NotesAppTheme.colorScheme.pinkish_purple,
        NotesAppTheme.colorScheme.baby_pink,
        NotesAppTheme.colorScheme.light_green,
        NotesAppTheme.colorScheme.lightest_red,
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            IconButton(onClick = { onBackCLick() }) {
                Image(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(NotesAppTheme.colorScheme.white)
                )
            }
            Spacer(modifier = Modifier.padding(start = 75.dp))
            Text(
                text = if (notesAddUiState?.notesData == null) "Add notes" else "Edit note",
                style = NotesAppTheme.typography.text_20_500,
                color = NotesAppTheme.colorScheme.white
            )
        }
        Image(
            painter = painterResource(id = R.drawable.notes),
            contentDescription = "Notes",
            modifier = Modifier
                .padding(top = 50.dp, bottom = 10.dp)
                .size(120.dp)
        )
        CustomEditText(
            value = notesAddUiState?.title ?: "",
            onValueChange = {
                onAction(NoteAddAndEditIntent.OnTitleValueChangeIntent(it))
            },
            backgroundColor = Color.Transparent,
            textStyle = NotesAppTheme.typography.text_20_500,
            content = {
                Text(
                    text = "Add title...",
                    style = NotesAppTheme.typography.text_20_500,
                    color = NotesAppTheme.colorScheme.white
                )
            },
            focusedLabelColor = NotesAppTheme.colorScheme.white,
            focusedTextColor = NotesAppTheme.colorScheme.white,
            unfocusedTextColor = NotesAppTheme.colorScheme.white,
            unfocusedLabelColor = NotesAppTheme.colorScheme.white,
            cursorColor = NotesAppTheme.colorScheme.white,
        )
        CustomEditText(
            value = notesAddUiState?.description ?: "",
            onValueChange = { onAction(NoteAddAndEditIntent.OnDescriptionValueChangeIntent(it)) },
            backgroundColor = Color.Transparent,
            content = {
                Text(
                    text = "Add des...",
                    style = NotesAppTheme.typography.text_16_500,
                    color = NotesAppTheme.colorScheme.white
                )
            },
            focusedLabelColor = NotesAppTheme.colorScheme.white,
            focusedTextColor = NotesAppTheme.colorScheme.white,
            unfocusedTextColor = NotesAppTheme.colorScheme.white,
            unfocusedLabelColor = NotesAppTheme.colorScheme.white,
            cursorColor = NotesAppTheme.colorScheme.white,
            imeAction = ImeAction.Default
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { showColorPicker = true }) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Pick Color",
                tint = NotesAppTheme.colorScheme.white
            )
        }
        val title = notesAddUiState?.title ?: ""
        val description = notesAddUiState?.description ?: ""
        val enable = title.length > 2 && description.length > 2
        val buttonText = if (notesAddUiState?.notesData == null) "Save" else "Update"
        RoundedCustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            buttonText = buttonText,
            isEnable = enable,
            onClick = {
                onAction(NoteAddAndEditIntent.OnSaveAndUpdateClickIntent)
                onBackCLick()
            }
        )
        if (showColorPicker) {
            ColorPickerBottomSheet(
                colors = selectedColor,
                sheetState = sheetState,
                onColorSelected = { onAction(NoteAddAndEditIntent.OnBackgroundChangeClickIntent(it)) },
                onDismiss = { showColorPicker = false }
            )
        }
    }
}



@Preview()
@Composable
private fun TestAddNotesPreview() {
    NotesTheme {
        TestAddNotesEditComponent (
            notesAddUiState = NotesAddUiState(),
            onAction = {},
            onBackCLick = {}
        )
    }
}