package com.notes.presentation_ui.notes_screen

import android.os.Bundle
import androidx.navigation.NavType
import com.notes.domain.room.NotesDataEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

data class NotesUiState(
   val notesUiList: List<NotesUiModel> = emptyList()
)

@Serializable
data class NotesUiModel(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val createdAt: Long? = null,
    val updatedAt: Long? = null,
    val backgroundColor: String? = null,
    val textColor: String? = null
)

fun NotesDataEntity.toNotesUiModel(): NotesUiModel {
    return NotesUiModel(
        id = id,
        title = title,
        description = description,
        createdAt = createdAt,
        updatedAt = updatedAt,
        backgroundColor = backgroundColor,
        textColor = textColor
    )
}

fun NotesUiModel.toNoteData(): NotesDataEntity {
    return NotesDataEntity(
        id = id ?: 0,
        title = title ?: "",
        description = description ?: "",
        createdAt = createdAt,
        updatedAt = updatedAt,
        backgroundColor = backgroundColor,
        textColor = textColor
    )
}

object CustomNavType {

    val NotesUiModelType = object : NavType<NotesUiModel?>(
        isNullableAllowed = true
    ) {
        override fun serializeAsValue(value: NotesUiModel?): String {
            val encodedJson = Json.encodeToString(value)
//            val uriEncoded = Uri.encode(encodedJson)
            return encodedJson
        }

        override fun parseValue(value: String): NotesUiModel? {
//            val decodeValue = Uri.decode(value)
            val jsonDecodedValue = Json.decodeFromString<NotesUiModel?>(value)
            return jsonDecodedValue
        }

        override fun put(bundle: Bundle, key: String, value: NotesUiModel?) {
            val encodedValue = Json.encodeToString(value)
//            val uriEncoded = Uri.encode(encodedValue)
            bundle.putString(key, encodedValue)
        }

        override fun get(bundle: Bundle, key: String): NotesUiModel? {
            val encodedData = bundle.getString(key) ?: return null
//            val decodedData = Uri.decode(encodedData)
            val parsedData = Json.decodeFromString<NotesUiModel?>(encodedData)
            return parsedData
        }
    }
}
