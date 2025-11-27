package com.notes.presentation_ui.notes_screen


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.notes.presentation_ui.add_update_screen.NotesAddAndEditScreen
import com.notes.presentation_ui.add_update_screen.NotesAddAndEditViewModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Composable
fun NotesApplicationComponent() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NotesRoutes
    ) {
        composable<NotesRoutes> {
            val notesViewModel: NotesViewModel = hiltViewModel()
            NotesScreen(
                viewModel = notesViewModel,
                onAddNewNotes = {
                    val route = NotesAddAndEditRoutes(notesData = null)
                    navController.navigate(route)
                },
                onNotesClick = { note ->
                    val route = NotesAddAndEditRoutes(notesData = note)
                    navController.navigate(route)
                    Log.d("TAG-----------------------------", "NotesApplicationComponent:${note?.id} ")
                }
            )
        }
        composable<NotesAddAndEditRoutes>(
            typeMap = mapOf(typeOf<NotesUiModel?>() to CustomNavType.NotesUiModelType)
        ) {
            val notesAddEditViewModel: NotesAddAndEditViewModel = hiltViewModel()
            NotesAddAndEditScreen(
                viewModel = notesAddEditViewModel,
                onBackCLick = navController::popBackStack
            )
        }
    }
}
@Serializable
object NotesRoutes

@Serializable
data class NotesAddAndEditRoutes(
   val notesData: NotesUiModel? = null
)