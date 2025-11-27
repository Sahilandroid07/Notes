package com.notes.presentation_ui.notes_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.notes.R
import com.notes.ui.theme.NotesTheme
import com.notes.ui.theme.util.custom.MultiItemTicker
import com.notes.ui.theme.util.custom.getRealTimeDate
import com.notes.ui.theme.util.custom_modifier.CustomEditTextWithLeadAndTrailIcon
import com.notes.ui.theme.util.custom_typo_color_shape.NotesAppTheme
import com.notes.ui.theme.util.toComposeColor

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel,
    onNotesClick: (NotesUiModel?) -> Unit,
    onAddNewNotes: () -> Unit,
) {
    val notesUiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = NotesAppTheme.colorScheme.white)
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        TestNotesComponent(
            notesUiSate = notesUiState,
            onAction = viewModel::onAction,
            onAddNewNotes = onAddNewNotes,
            onNotesClick = onNotesClick
        )
    }
}

@Composable
fun TestNotesComponent(
    modifier: Modifier = Modifier,
    notesUiSate: NotesUiState,
    onNotesClick: (NotesUiModel?) -> Unit,
    onAddNewNotes: () -> Unit = {},
    onAction: (NotesIntent) -> Unit
) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var isClicked by rememberSaveable { mutableStateOf(false) }
    val filterNotesDate = remember(notesUiSate, searchQuery) {
        notesUiSate.notesUiList.filter { it.title?.contains(searchQuery,  ignoreCase = true) == true }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            TestNotesSearchBarComponent(
                modifier = Modifier,
                isClicked = isClicked,
                onClick = { isClicked = !isClicked },
                searchText = searchQuery,
                onValueChange = { searchQuery = it }
            )
            if (filterNotesDate.isNotEmpty()) {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    verticalItemSpacing = 16.dp,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 150.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(
                        span = { _, _ ->
                            if (isClicked) StaggeredGridItemSpan.SingleLane
                            else StaggeredGridItemSpan.FullLine
                        },
                        items = filterNotesDate,
                        key = { _, filterNotesDate -> filterNotesDate.id!! }
                    ) { index, noteModel ->
//                    val gyroEnable = filterNotesDate.isNotEmpty() && index == 0
//                    val conditionalModifier = if (gyroEnable) Modifier.gyroScope(
//                        enableGyro = true,
//                        context = context
//                    )
//                    else Modifier
                        YourNotesComponentItem(
                            modifier = Modifier,
                            notesUiModel = noteModel,
                            onDeleteCLick = { onAction(NotesIntent.OnDeleteClickIntent(noteModel)) } ,
                            onNotesClick = { onNotesClick(noteModel) }
                        )
                    }
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    EmptyListComponent(
                        modifier = Modifier
                    )
                }
            }
        }
        TestAddNotesBottomComponent(
            onAddNewNotes = onAddNewNotes,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
fun EmptyListComponent(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.notes),
            contentDescription = "notes",
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "Please add your notes",
            style = NotesAppTheme.typography.text_20_500,
            color = NotesAppTheme.colorScheme.black.copy(alpha = 0.4f),
            modifier = Modifier.padding(top = 25.dp)
        )
    }
}

@Composable
fun TestNotesSearchBarComponent(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onValueChange: (String) -> Unit = {},
    isClicked: Boolean,
    onClick: () -> Unit
) {
    val iconRes = if (isClicked) R.drawable.menu else R.drawable.grid
    val brush = Brush.verticalGradient(
        colors = listOf(
            Color.White,
            Color.Transparent
        )
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(brush = brush)
            .padding(vertical = 24.dp, horizontal = 16.dp)
    ) {
        CustomEditTextWithLeadAndTrailIcon(
            focusedTextColor = NotesAppTheme.colorScheme.black,
            focusedLabelColor = NotesAppTheme.colorScheme.black,
            unfocusedTextColor = NotesAppTheme.colorScheme.black,
            unfocusedLabelColor = NotesAppTheme.colorScheme.black,
            cursorColor = NotesAppTheme.colorScheme.black,
            onValueChange = onValueChange,
            value = searchText,
            leadingIcon = R.drawable.ic_search,
            trailingIcon = iconRes,
            trailingIconClick = { onClick() },
            content = {
                MultiItemTicker(
                    items = textList
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestAddNotesBottomComponent(
    modifier: Modifier = Modifier,
//    onAction: (NotesIntent) -> Unit = {},
    onAddNewNotes: () -> Unit = {},
//    callApiClick: () -> Unit = {}
){
    Box(
        modifier = modifier
            .background(
                color = Color.Transparent
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp)
                .background(
                    color = NotesAppTheme.colorScheme.orange,
                    shape = NotesAppTheme.shapes.top_rounded_corner_24
                )
                .padding(16.dp)
        ) {
            IconButton(onClick = {  }) {
                Image(imageVector = Icons.Default.AddCircle, contentDescription = "Add")
            }
            IconButton(onClick = {}) {
                Image(imageVector = Icons.Default.Favorite, contentDescription = "Favourite")
            }
            IconButton(onClick = {}) {
                Image(imageVector = Icons.Default.Edit, contentDescription = "Add")
            }
        }
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = "Add",
            modifier = Modifier
                .padding(end = 35.dp)
                .background(
                    color = NotesAppTheme.colorScheme.orange,
                    shape = NotesAppTheme.shapes.rounded_corner_20
                )
                .border(
                    width = 3.dp,
                    color = NotesAppTheme.colorScheme.white,
                    shape = NotesAppTheme.shapes.rounded_corner_20
                )
                .padding(10.dp)
                .size(40.dp)
                .align(Alignment.TopEnd)
                .clip(NotesAppTheme.shapes.rounded_corner_20)
                .clickable { onAddNewNotes() }

        )
    }
}


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun YourNotesComponentItem(
    modifier: Modifier = Modifier,
    notesUiModel: NotesUiModel?,
    onDeleteCLick: () -> Unit,
    onNotesClick: (NotesUiModel?) -> Unit,
    contentAlignment: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    val backgroundColor = runCatching {
        notesUiModel?.backgroundColor?.toComposeColor() ?: NotesAppTheme.colorScheme.white
    }.getOrElse { NotesAppTheme.colorScheme.white }

    val sheetState = rememberModalBottomSheetState()
    var showSheet by remember { mutableStateOf(false) }
    val shape = NotesAppTheme.shapes.rounded_corner_24
    Box(
        modifier = modifier
            .shadow(elevation = 8.dp, shape = shape)
            .background(
                color = backgroundColor,
                shape = shape
            )
            .border(
                width = 1.dp,
                color = NotesAppTheme.colorScheme.black.copy(alpha = 0.2f),
                shape = shape
            )
            .clip(shape = shape)
            .combinedClickable(
                onClick = { onNotesClick(notesUiModel) },
                onLongClick = {
                    showSheet = true
                }
            )
            .padding(16.dp)
    ){
        Column {
            Text(
                text = notesUiModel?.createdAt?.let { getRealTimeDate(it) }.toString(),
                color = NotesAppTheme.colorScheme.black,
                style = NotesAppTheme.typography.text_14_500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(contentAlignment)
            )
            Text(
                text = notesUiModel?.title ?: "",
                color = NotesAppTheme.colorScheme.black,
                style = NotesAppTheme.typography.text_20_500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 5.dp)
            )
            Text(
                text = notesUiModel?.description ?: "",
                color = NotesAppTheme.colorScheme.black,
                style = NotesAppTheme.typography.text_16_500,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
        if (showSheet)
            DeleteNotesBottomSheet(
                showSheet = { showSheet = false },
                sheetState = sheetState,
                notesUiModel = notesUiModel,
                onDeleteCLick = onDeleteCLick,
                onNoThanksClick = { showSheet = false },
            )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteNotesBottomSheet(
    modifier: Modifier = Modifier,
    showSheet: () -> Unit,
    notesUiModel: NotesUiModel?,
    sheetState: SheetState,
    onDeleteCLick: () -> Unit,
    onNoThanksClick: () -> Unit
) {
    val annotatedString = buildAnnotatedString {
        append("Do you want to delete\n")
        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
            append(notesUiModel?.title)
        }
        append(" note!")
    }
    ModalBottomSheet(
        onDismissRequest = showSheet,
        sheetState = sheetState,
        containerColor = NotesAppTheme.colorScheme.white,
        shape = NotesAppTheme.shapes.rounded_corner_24
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = annotatedString,
                style = NotesAppTheme.typography.text_18_500,
                color = NotesAppTheme.colorScheme.black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Text(
                    text = "Delete it",
                    style = NotesAppTheme.typography.text_14_500,
                    color = NotesAppTheme.colorScheme.black,
                    modifier = Modifier
                        .background(
                            shape = NotesAppTheme.shapes.rounded_corner_32,
                            color = NotesAppTheme.colorScheme.button_green
                        )
                        .clickable {
                            onDeleteCLick()
                            showSheet()
                        }
                        .padding(horizontal = 45.dp, vertical = 20.dp)
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "No, Thanks",
                    style = NotesAppTheme.typography.text_14_500,
                    color = NotesAppTheme.colorScheme.black,
                    modifier = Modifier
                        .background(
                            shape = NotesAppTheme.shapes.rounded_corner_32,
                            color = NotesAppTheme.colorScheme.white
                        )
                        .border(
                            width = 1.dp,
                            shape = NotesAppTheme.shapes.rounded_corner_32,
                            color = NotesAppTheme.colorScheme.light_green
                        )
                        .clickable { onNoThanksClick() }
                        .padding(horizontal = 35.dp, vertical = 20.dp)
                )
            }
        }
    }
}

private val textList = listOf(
    "Find your notes",
    "Find your docs",
    "Find your ideas"
)

@Preview
@Composable
private fun TestNotesPreview() {
    val testUiState = NotesUiState()
    NotesTheme {
        TestNotesComponent(
            notesUiSate = testUiState,
            onAddNewNotes = {},
            onNotesClick = {},
            onAction = {}
        )
    }
}