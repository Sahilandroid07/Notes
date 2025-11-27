package com.notes.navigation_routes

import kotlinx.serialization.Serializable

sealed interface ComposeRoutes {

    @Serializable
    data object Splash: ComposeRoutes

    @Serializable
    data object NotesScreen: ComposeRoutes

    @Serializable
    data object AddAndEditNoteScreen: ComposeRoutes

}